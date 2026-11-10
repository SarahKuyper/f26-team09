<!DOCTYPE html>
<html>
<head>
    <title>PrestoPaws</title>
</head>
<body>
    <h1>PrestoPaws</h1>
    <h2>Provider</h2>
<ul>
    <li><a href="/providers/new">Create a provider profile</a></li>
</ul>

<#if providers?? && providers?size gt 0>
    <h3>Existing providers</h3>
    <ul>
        <#list providers as p>
            <li>
                <a href="/providers/${p.id}">${p.name}</a>
                — <a href="/providers/${p.id}/items">items</a>
            </li>
        </#list>
    </ul>
</#if>
</body>
</html>