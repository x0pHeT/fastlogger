package com.db.logger.io.storage;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Provides fast low-level access to underlying NIO buffer. Expected usage scenario:
 * <pre>
 *     final RawWriter w = ...;
 *     ByteBuffer buffer = w.buffer()
 *     while( hasDataToWrite() ){
 *        //write chunk data to buffer
 *        if(!buffer.hasRemaining()){
 *            w.flush(); //this will flush content and rewinds buffer
 *            buffer = w.buffer();//need to re-get buffer
 *        }
 *     }
 * w.close();
 * </pre>
 *
 * @author cherrus
 *         created 11/14/13 at 10:16 AM
 */
public interface RawWriter extends Flushable, Closeable/*, AutoCloseable*/ {

    public ByteBuffer buffer();

    /**
     * Flush current buffer content to underlying storage. Buffer must be re-get
     * by {@linkplain #}
     *
     * @throws java.io.IOException
     */
    @Override
    public void flush() throws IOException;

    @Override
    public void close() throws IOException;
}
