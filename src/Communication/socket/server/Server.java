package Communication.socket.server;

import Communication.socket.api.MessageEncoderDecoder;
import Communication.socket.api.MessagingProtocol;
import Communication.socket.server.Blocking.BaseServer;
import Communication.socket.server.Blocking.BlockingConnectionHandler;
import Communication.socket.server.Reactor.Reactor;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public interface Server<T> extends Closeable {

    /**
     * The main loop of the server, Starts listening and handling new clients.
     */
    void serve();




    // ----------------------------------------------------------------- server builders -----------------------------------------------------------------


    /**
     *This function returns a new instance of a thread per client pattern server
     * @param port The port for the server socket
     * @param protocolFactory A factory that creats new MessagingProtocols
     * @param encoderDecoderFactory A factory that creats new MessageEncoderDecoder
     * @param <T> The Message Object for the protocol
     * @return A new Thread per client server
     */
    public static <T> Server<T>  threadPerClient(
            int port,
            Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encoderDecoderFactory) {

        return new BaseServer<T>(port, protocolFactory, encoderDecoderFactory) {
            @Override
            protected void execute(BlockingConnectionHandler<T> handler) {
                new Thread(handler).start();
            }
        };

    }

    /**
     *This function returns a new instance of a threadpoll pattern server
     * @param nThreads Number of threads available for protocol processing
     * @param port The port for the server socket
     * @param protocolFactory A factory that creats new MessagingProtocols
     * @param encoderDecoderFactory A factory that creats new MessageEncoderDecoder
     * @param <T> The Message Object for the protocol
     * @return A new Threadpoll server
     */
    public static <T> Server<T>  threadPoll(
            int nThreads,
            int port,
            Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encoderDecoderFactory) {

        return new BaseServer<T>(port, protocolFactory, encoderDecoderFactory) {

            private final ExecutorService threads = Executors.newFixedThreadPool(nThreads);

            @Override
            protected void execute(BlockingConnectionHandler<T> handler) { threads.execute(handler); }
        };

    }

    /**
     * This function returns a new instance of a reactor pattern server
     * @param nThreads Number of threads available for protocol processing
     * @param port The port for the server socket
     * @param protocolFactory A factory that creats new MessagingProtocols
     * @param encoderDecoderFactory A factory that creats new MessageEncoderDecoder
     * @param <T> The Message Object for the protocol
     * @return A new reactor server
     */
    public static <T> Server<T> reactor(
            int nThreads,
            int port,
            Supplier<MessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encoderDecoderFactory) {
        return new Reactor<>(nThreads, port, protocolFactory, encoderDecoderFactory);
    }

}
