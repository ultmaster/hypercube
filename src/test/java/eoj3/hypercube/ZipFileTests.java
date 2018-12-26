package eoj3.hypercube;

import eoj3.hypercube.services.TestsAddService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ZipFileTests {

    @Autowired
    TestsAddService testsAddService;

    final private String workingDirectory;

    public ZipFileTests() {
        this.workingDirectory = System.getProperty("user.dir");
    }

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(new File(Paths.get(this.workingDirectory, "workspace").toString()));
    }

    @Test
    public void testZipFile() {
        try {
            InputStream inputStream = new FileInputStream(Paths.get(this.workingDirectory, "src", "test", "resources", "zip-tests.zip").toString());
            MockMultipartFile file = new MockMultipartFile("zip-tests", inputStream);
            TestsAddService.FormWrapper wrapper = new TestsAddService.FormWrapper();
            wrapper.setFiles(new MockMultipartFile[]{file});
            testsAddService.processForm(wrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
