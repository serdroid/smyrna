package info.serdroid.smyrna.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(asyncSupported = true, value = "/async")
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = -5433249903380295426L;
	private static final Logger logger = LoggerFactory.getLogger(AsyncServlet.class);

	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AsyncContext asyncContext = req.startAsync();
		logger.info("dispatching request to worker thread pool");
		fixedThreadPool.submit(() -> {
			doSomeOperation(asyncContext);
		});
	}
	
	private void doSomeOperation(AsyncContext asyncContext) {
		logger.info("running operation");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		try {
			PrintWriter writer = asyncContext.getResponse().getWriter();
			writer.write("async operation completed");
			writer.close();
			asyncContext.complete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
