import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;


@RunWith(SpringRunner.class)
public class RunAllTests {
    SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public void runAll() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(selectPackage("eulife.tests.domain"))
                .selectors(selectPackage("eulife.tests.services"))
                .filters(includeClassNamePatterns(".*Test"))
                .build();

        try (LauncherSession session = LauncherFactory.openSession()) {
            Launcher launcher = session.getLauncher();
            launcher.registerTestExecutionListeners(listener);
            TestPlan testPlan = launcher.discover(request);

            launcher.execute(testPlan);
            launcher.execute(request);
        }


    }

    public static void main(String[] args) {
        var runAllTests = new RunAllTests();
        runAllTests.runAll();

        TestExecutionSummary testExecutionSummary = runAllTests.listener.getSummary();
        testExecutionSummary.printTo(new PrintWriter(System.out));
    }
}
