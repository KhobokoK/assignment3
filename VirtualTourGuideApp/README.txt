=== Virtual Tour Guide App ===

To COMPILE:
javac --module-path "C:\Path\To\javafx-sdk-24\lib" --add-modules javafx.controls,javafx.media -d out src\application\*.java

To RUN:
java --module-path "C:\Path\To\javafx-sdk-24\lib" --add-modules javafx.controls,javafx.media -cp out application.Main

Replace the path with your actual JavaFX SDK path.