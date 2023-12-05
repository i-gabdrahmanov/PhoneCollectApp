<!DOCTYPE html>
<html>
<head>
    <title>Добавление номера телефона</title>
</head>
<body>
<h2>Введите номер телефона</h2>
<form action="/addPhoneNumber" method="post">
    <div>
        <label for="phoneNumber">Номер телефона:</label>
        <input type="text" id="phoneNumber" name="phoneNumber">
    </div>
    <input type="submit" value="Добавить">
</form>
<#if success>
    <p style="color: green;">Номер добавлен</p>
</#if>
</body>
</html>