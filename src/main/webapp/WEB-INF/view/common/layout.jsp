<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1"/>
  <title><tiles:getAsString name="title" /></title>
  <tiles:insert attribute="header" ignore="true"/>
</head>
<body>
  <tiles:insert attribute="content"/>
</body>
</html>