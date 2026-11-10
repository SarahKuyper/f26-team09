<!DOCTYPE html>
<html>
<head>
    <title>${editing?then("Edit Provider", "New Provider")} - PrestoPaws</title>
</head>
<body>
    <h1>PrestoPaws</h1>
    <h2>${editing?then("Edit Provider Profile", "Create Provider Profile")}</h2>

    <#if editing>
        <form action="/providers/${provider.id}" method="post">
    <#else>
        <form action="/providers" method="post">
    </#if>

        <div>
            <label>Business name:</label><br>
            <input type="text" name="name" value="${provider.name!}" />
            <#if errors?? && errors.hasFieldErrors("name")>
                <p style="color: red;">${errors.getFieldError("name").defaultMessage}</p>
            </#if>
        </div>

        <div>
            <label>Email:</label><br>
            <input type="email" name="email" value="${provider.email!}" />
            <#if errors?? && errors.hasFieldErrors("email")>
                <p style="color: red;">${errors.getFieldError("email").defaultMessage}</p>
            </#if>
        </div>

        <div>
            <label>Password:</label><br>
            <input type="password" name="password" value="" />
            <#if errors?? && errors.hasFieldErrors("password")>
                <p style="color: red;">${errors.getFieldError("password").defaultMessage}</p>
            </#if>
        </div>

        <button type="submit">${editing?then("Save Changes", "Create Profile")}</button>
    </form>
</body>
</html>