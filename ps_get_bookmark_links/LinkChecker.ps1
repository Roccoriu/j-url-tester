#Version 0.1, basic functionallity check
#Authors: Rocco Ciccone & Christian Rhyner

#Defining global variables
#--------------------------------------------------------------------------------------------------------------------------------

[string]$name
[string]$url = "URL"
[string]$finalUrl
[string]$printUrl

#Defining Functions
#--------------------------------------------------------------------------------------------------------------------------------

#Main code
#--------------------------------------------------------------------------------------------------------------------------------
#New-Item -ItemType File .\test.txt

Get-ChildItem ([Environment]::GetFolderPath('Favorites')) -File -Recurse | ForEach-Object {
    $name = $_.Name
    $url = ($_ | Select-String "^URL").Line.Trim("URL=")
    echo $url >> .\test.txt 
}
