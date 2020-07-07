package logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.anasdidi.backend.common.CommonConstant;
import com.anasdidi.backend.common.CommonVerticle;

import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

public class LoggerVerticle extends CommonVerticle {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    EventBus eventBus = vertx.eventBus();
    eventBus.consumer(CommonConstant.Event.LOGGER_DEBUG.key()).handler(this::eventLogDebug);
  }

  private void eventLogDebug(Message<Object> msg) {
    String body = (String) msg.body();
    System.out.println(FORMATTER.format(LocalDateTime.now()) + " DEBUG " + body);
  }
}
