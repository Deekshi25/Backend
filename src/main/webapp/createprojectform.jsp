
<html>
<head>
<style>
 <style>
    .form-container {
   	 max-width: 440px; /* Max width of the form */
   	 margin: auto;
   	 padding: 20px;
     background-color: white;
     border-radius: 8px;
     box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
	input[type=text], input[type=password], input[type=number], input[type=email], select, input[type=date], textarea {
    width: calc(100% - 10px); /* Adjust width */
    max-width: 440px; /* Limiting the maximum width */
    padding: 10px;
    margin: 8px 0;
    display: block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
  }
  .button {
    background-color: black;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 10px 40px;
    cursor: pointer;
  }
  label {
    padding: 12px 0;
    display: block;
    font-weight: bold;
  }
  .radio-label {
    padding: 0;
    display: inline;
    font-weight: normal;
}

  h3 {
          text-align: center;
          text-decoration: underline;
          font-size: 24px;
          color: #333;
          margin-top: 30px;
          margin-bottom: 20px;
  }
</style>
</head>

<center>
<form action="/createproject" method="post" enctype="multipart/form-data">
    <label for="title">Title:</label>
    <input type="text" name="title" required><br>

    <label for="description">Description:</label>
    <textarea name="description" required></textarea><br>

    <label for="startDate">Start Date:</label>
    <input type="date" name="startDate" required><br>

    <label for="endDate">End Date:</label>
    <input type="date" name="endDate"><br>

   <!--  <label for="files">Upload Files:</label>
    <input type="file" name="files" multiple><br> -->

    <label for="images">Upload Images:</label>
    <input type="file" name="images" multiple><br>

    <label for="pdf">Upload PDF:</label>
    <input type="file" name="pdf" accept=".pdf"><br>

    <label for="zip">Upload ZIP:</label>
    <input type="file" name="zip" accept=".zip"><br>

    <input class="button" type="submit">Create Project</button>
</form>
</center>
</html>

