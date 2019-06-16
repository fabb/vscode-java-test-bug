import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverParameterResolver implements ParameterResolver, AfterEachCallback {

    private static final String DRIVER_KEY = "my-webDriver";

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> parameterType = parameterContext.getParameter().getType();
        return parameterType.isAssignableFrom(WebDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> parameterType = parameterContext.getParameter().getType();
        if (parameterType.isAssignableFrom(WebDriver.class)) {
            // some more complicated logic for using different drivers, or reading out options from annotations was omitted
            WebDriver driver = new FirefoxDriver();
            getStore(extensionContext).put(DRIVER_KEY, driver);
            return driver;
        } else {
            return null;
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        WebDriver driver = getDriverFromStore(extensionContext);
        if (driver != null) {
            driver.quit();
        }
    }

    private static Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create(DriverParameterResolver.class));
    }

    private static WebDriver getDriverFromStore(ExtensionContext context) {
        return getStore(context).get(DRIVER_KEY, WebDriver.class);
    }

}
