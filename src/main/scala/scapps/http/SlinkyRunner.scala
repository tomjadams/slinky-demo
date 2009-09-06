package scapps.http

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletHolder, ServletContextHandler}

object SlinkyRunner {
  def main(args: Array[String]) {

    val server: Server = new Server(8081)
    val handler: ServletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS | ServletContextHandler.NO_SECURITY)

    val holder = new ServletHolder(classOf[scalaz.http.servlet.StreamStreamServlet])
    holder.setInitParameter("application", "slinkydemo.http.SlinkyDemoApplication")

    handler.addServlet(holder, "/*")
    server.setHandler(handler);

    server.start();
    server.join();
  }
}
