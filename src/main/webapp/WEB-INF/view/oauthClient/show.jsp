<h1>OAuth details for ${f:h(clientApplication.name)}</h1>
<p>
  <strong>Consumer Key:</strong>
  <code>${f:h(clientApplication.key)}</code>
</p>
<p>
  <strong>Consumer Secret:</strong>
  <code>${f:h(clientApplication.secret)}</code>
</p>
<p>
  <strong>Request Token URL</strong>
  <code>${f:h(clientApplication.oauthServer.requestTokenPath)}</code>
</p>
<p>
  <strong>Access Token URL</strong>
  <code>${f:h(clientApplication.oauthServer.accessTokenPath)}</code>
</p>
<p>
  <strong>Authorize URL</strong>
  <code>${f:h(clientApplication.oauthServer.authorizePath)}</code>
</p>

<p>
  We support hmac-sha1 (recommended) as well as plain text in ssl mode.
</p>
<s:link href="edit/${id}">Edit</s:link> |
<s:link href="index">Back</s:link>