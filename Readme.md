# Bug Reproduction Repo for a bug in vscode-java-test

Bug Ticket: https://github.com/microsoft/vscode-java-test/issues/661

## Reproduction Steps

1. Execute `mvn install -P download-drivers` to download geckodriver
2. Try to run `VSCodeTest` using VSCode with the lens of vscode-java-test

## Expected Output

The tests should start Firefox and run (and fail due to the assertion in the test)

## Actual Output

Not even Firefox is started, instead this error message is shown:

```
org.junit.jupiter.api.extension.ParameterResolutionException: Failed to resolve parameter [org.openqa.selenium.WebDriver arg0] in method [void VSCodeTest.testMyPage(org.openqa.selenium.WebDriver)]: The path to the driver executable must be set by the webdriver.gecko.driver system property; for more information, see https://github.com/mozilla/geckodriver. The latest version can be downloaded from https://github.com/mozilla/geckodriver/releases
```

## Problem Analysis

The test project runs fine in IntelliJ. When editing the pom.xml, and removing the mac/linux/win32/win64 profiles, it also does not run in IntelliJ anymore with the same error.
Therefore it seems like vscode-java-test does not regard the `<activation>` entries in profiles.
