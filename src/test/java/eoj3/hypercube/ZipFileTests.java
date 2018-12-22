package eoj3.hypercube;

import eoj3.hypercube.services.TestsAddService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.EdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ZipFileTests {

    @Autowired
    TestsAddService testsAddService;

    @Before
    public void setUp() throws Exception {
        FileUtils.cleanDirectory(new File("./workspace"));
    }

    @Test
    public void testZipFile() {
        try {
            InputStream inputStream = new FileInputStream("./src/test/resources/zip-tests.zip");
            MockMultipartFile file = new MockMultipartFile("zip-tests", inputStream);
            TestsAddService.FormWrapper wrapper = new TestsAddService.FormWrapper();
            wrapper.setFiles(new MockMultipartFile[]{file});
            testsAddService.processForm(wrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
