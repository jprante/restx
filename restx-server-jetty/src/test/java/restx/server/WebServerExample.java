package restx.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import restx.RestxMainRouter;
import restx.RestxRequest;
import restx.RestxRequestMatch;
import restx.RestxRouter;
import restx.StdRestxMainRouter;
import restx.entity.MatchedEntityOutputRoute;
import restx.servlet.AbstractRestxMainRouterServlet;

import java.io.IOException;

/**
 * User: xavierhanin
 * Date: 2/16/13
 * Time: 4:24 PM
 */
@SuppressWarnings("unchecked")
public class WebServerExample {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final RestxMainRouter ROUTER = StdRestxMainRouter.builder()
            .addRouter(RestxRouter.builder()
                    .withMapper(mapper)
                    .GET("/route1/{id}", new MatchedEntityOutputRoute() {
                        @Override
                        public Optional route(RestxRequest restxRequest, RestxRequestMatch match) {
                            return Optional.of(ImmutableMap.of("id", match.getPathParam("id")));
                        }
                    })
                    .GET("/route2", new MatchedEntityOutputRoute() {
                        @Override
                        public Optional<?> route(RestxRequest restxRequest, RestxRequestMatch match) throws IOException {
                            return Optional.of(ImmutableMap.of("path", "route2"));
                        }
                    })
                    .GET("/route3", new MatchedEntityOutputRoute() {
                        @Override
                        public Optional<?> route(RestxRequest restxRequest, RestxRequestMatch match) throws IOException {
                            return Optional.of(ImmutableMap.of("path", "route3"));
                        }
                    })
                    .build())
            .build();


    public static void main(String[] args) throws Exception {
      WebServer webServer = new JettyWebServer(
              "restx/src/test/resources/restx/server/WebServerExample-web.xml",
              ".", 8080, "localhost");
      webServer.startAndAwait();
    }

    public static class Servlet extends AbstractRestxMainRouterServlet {
        public Servlet() {
            super(ROUTER);
        }
    }

}
