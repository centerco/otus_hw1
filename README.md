# GeoAnalyzer
### v.1.0 by Andrey Chuchalov
### Description
GeoAnalyzer is a tool that provides a short sorting process of the specified raw data. As a result it writes a simple selection of 10 biggest countries on the Africa continent.
### Compilation and packaging
Use the sbt tool to package this project:
```shell
sbt clean compile assembly
```
### Running
Use the following syntax to launch the application
```shell
java -jar hw1-geo-assembly-0.1.jar countries.json.txt output.txt
```
Jar file accepts 2 command line parameters: source file with the raw data and the destination file name.

Use vim or any other text editor to verify the final results.

Output file contains JSon string with the result of the analyzed data:
```text
[{"name":"Algeria","capital":"Algiers","area":2381741},{"name":"DR Congo","capital":"Kinshasa","area":2344858},{"name":"Sudan","capital":"Khartoum","area":1886068},{"name":"Mali","capital":"Bamako","area":1240192},{"name":"South Africa","capital":"Pretoria","area":1221037},{"name":"Tanzania","capital":"Dodoma","area":945087},{"name":"Nigeria","capital":"Abuja","area":923768},{"name":"Namibia","capital":"Windhoek","area":825615},{"name":"Zambia","capital":"Lusaka","area":752612},{"name":"Somalia","capital":"Mogadishu","area":637657}]
```