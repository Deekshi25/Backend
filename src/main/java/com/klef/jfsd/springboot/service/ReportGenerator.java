package com.klef.jfsd.springboot.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.ProjectFeedback;
import com.klef.jfsd.springboot.repository.ProjectFeedbackRepository;
import com.klef.jfsd.springboot.repository.ProjectRepository;

@Service
public class ReportGenerator {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectFeedbackRepository projectFeedbackRepository;

    public Blob generatePDFReport(int projectId) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set margins
            float margin = 50;
            float yPosition = page.getMediaBox().getHeight() - margin;
            float pageWidth = page.getMediaBox().getWidth();
            float contentWidth = pageWidth - 2 * margin;

            // Title (Centered)
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            PDColor titleColor = new PDColor(new float[]{0, 0, 1}, PDDeviceRGB.INSTANCE); // Blue
            contentStream.setNonStrokingColor(titleColor);

            contentStream.beginText();
            String title = "Project Report for Project ID: " + projectId;
            float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * 18;
            float titleX = (pageWidth - titleWidth) / 2; // Center horizontally
            contentStream.newLineAtOffset(titleX, yPosition);
            contentStream.showText(title);
            contentStream.endText();

            // Separator line
            yPosition -= 30; // Adjust spacing below the title
            contentStream.setLineWidth(1);
            contentStream.moveTo(margin, yPosition);
            contentStream.lineTo(pageWidth - margin, yPosition);
            contentStream.stroke();
            yPosition -= 20;

            // Get project details from the database
            Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

            // Project Details
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            PDColor textColor = new PDColor(new float[]{0.2f, 0.2f, 0.2f}, PDDeviceRGB.INSTANCE); // Dark Gray
            contentStream.setNonStrokingColor(textColor);

            yPosition = writeWrappedTextWithOverflow(document, contentStream, "Project Title: " + project.getTitle(), margin, yPosition, contentWidth, margin, PDType1Font.HELVETICA, 12);
            yPosition = writeWrappedTextWithOverflow(document, contentStream, "Project Description: " + project.getDescription(), margin, yPosition, contentWidth, margin, PDType1Font.HELVETICA, 12);
            yPosition = writeWrappedTextWithOverflow(document, contentStream, "Project Phase: " + project.getPhase(), margin, yPosition, contentWidth, margin, PDType1Font.HELVETICA, 12);
            yPosition = writeWrappedTextWithOverflow(document, contentStream, "Project Status: " + (project.isCheckStatus() ? "Completed" : "In Progress"), margin, yPosition, contentWidth, margin, PDType1Font.HELVETICA, 12);
            yPosition = writeWrappedTextWithOverflow(document, contentStream, "Student ID: " + project.getStudentId(), margin, yPosition, contentWidth, margin, PDType1Font.HELVETICA, 12);

            // Feedback Section
            yPosition -= 20; // Adjust yPosition for feedback section
            List<ProjectFeedback> feedbacks = projectFeedbackRepository.findByProjectid(projectId);

            if (feedbacks != null && !feedbacks.isEmpty()) {
                yPosition = writeWrappedTextWithOverflow(document, contentStream, "Feedbacks:", margin, yPosition, contentWidth, margin, PDType1Font.HELVETICA_BOLD, 14);

                // Feedback content
                int i = 1;
                for (ProjectFeedback feedback : feedbacks) {
                    yPosition = writeWrappedTextWithOverflow(document, contentStream, "Feedback " + i + ":", margin, yPosition, contentWidth, margin, PDType1Font.HELVETICA, 12);
                    yPosition = writeWrappedTextWithOverflow(document, contentStream, "    Comments: " + feedback.getComments(), margin + 10, yPosition, contentWidth - 10, margin, PDType1Font.HELVETICA, 12);
                    yPosition -= 10; // Adjust spacing for the next feedback
                    i++;
                }
            }

            contentStream.close();

            // Convert the PDF document to a byte array (Blob)
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            byte[] pdfBlob = byteArrayOutputStream.toByteArray(); // PDF as byte array

            // Convert byte array to SQL Blob
            Blob blob = new SerialBlob(pdfBlob);

            // Save Blob to the Project model
            project.setReportCard(blob);
            projectRepository.save(project);

            return blob;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private float writeWrappedTextWithOverflow(PDDocument document, PDPageContentStream contentStream, String text, float x, float y, float maxWidth, float margin, PDType1Font font, float fontSize) throws Exception {
        List<String> lines = wrapText(text, maxWidth, font, fontSize);

        for (String line : lines) {
            if (y < margin) { // If text overflows the page
                contentStream.close(); // Close the current content stream
                PDPage newPage = new PDPage(); // Create a new page
                document.addPage(newPage);
                contentStream = new PDPageContentStream(document, newPage); // Start a new content stream
                y = newPage.getMediaBox().getHeight() - margin; // Reset y-position to the top of the new page
            }

            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(line);
            contentStream.endText();
            y -= fontSize + 5; // Move down for the next line
        }

        return y; // Return the updated y-position
    }

    private List<String> wrapText(String text, float width, PDType1Font font, float fontSize) throws IOException {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            String testLine = line + (line.length() == 0 ? "" : " ") + word;
            float textWidth = font.getStringWidth(testLine) / 1000 * fontSize;
            if (textWidth > width) {
                lines.add(line.toString());
                line = new StringBuilder(word);
            } else {
                line.append((line.length() == 0 ? "" : " ") + word);
            }
        }
        if (line.length() > 0) {
            lines.add(line.toString());
        }

        return lines;
    }
}
