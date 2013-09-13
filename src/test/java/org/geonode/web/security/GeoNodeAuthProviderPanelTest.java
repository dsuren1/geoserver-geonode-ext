package org.geonode.web.security;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TestPanelSource;
import org.geonode.security.GeoNodeAuthProviderConfig;
import org.geoserver.data.test.SystemTestData;
import org.geoserver.web.GeoServerWicketTestSupport;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContextHolder;

public class GeoNodeAuthProviderPanelTest extends GeoServerWicketTestSupport {
    @SuppressWarnings("serial")
    private static TestPanelSource createProviderSource(GeoNodeAuthProviderConfig config) {
        return new TestPanelSource() {
            public Panel getTestPanel(String id) {
                GeoNodeAuthProviderConfig config = new GeoNodeAuthProviderConfig();
                return new FormComponentTestingPanel(id, 
                    new GeoNodeAuthProviderPanel("formComponentPanel",
                        new Model<GeoNodeAuthProviderConfig>(config)));
            }
        };
    }
    
    @Override
    protected void onTearDown(SystemTestData testData) throws Exception {
        super.onTearDown(testData);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    public void testVisitPanel() {
        GeoNodeAuthProviderConfig config = new GeoNodeAuthProviderConfig();
        login();
        tester.startPanel(createProviderSource(config));
        tester.assertComponent("panel:formComponentPanel:baseUrl", TextField.class);
    }
}
