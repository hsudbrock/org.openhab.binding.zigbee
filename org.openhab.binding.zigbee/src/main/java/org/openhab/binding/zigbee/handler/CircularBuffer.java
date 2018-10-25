package org.openhab.binding.zigbee.handler;

/**
 * Implementation of a circular buffer. This buffer is not thread-safe, consumers must synchronize access to the buffer
 * on their own.
 *
 * @author Henning Sudbrock - extracted this class from the {@link ZigBeeSerialPort}.
 */
public class CircularBuffer {

    /**
     * The length of the buffer
     */
    private static final int BUFFER_LEN = 512;

    /**
     * The circular fifo queue for data
     */
    private final int[] buffer = new int[BUFFER_LEN];

    /**
     * The buffer end pointer (where we put new data)
     */
    private int end = 0;

    /**
     * The buffer start pointer (where we take data)
     */
    private int start = 0;

    /**
     * Tests for emptyness.
     *
     * @return true if and only if the buffer is empty
     */
    public boolean isEmpty() {
        return start == end;
    }

    /**
     * Put a byte into the buffer.
     */
    public void put(int value) {
        buffer[end++] = value;
        if (end >= BUFFER_LEN) {
            end = 0;
        }
    }

    /**
     * Take a byte from the buffer.
     *
     * @return the byte
     */
    public int take() {
        int value = buffer[start++];
        if (start >= BUFFER_LEN) {
            start = 0;
        }
        return value;
    }

    /**
     * Reset the buffer to empty state.
     */
    public void purge() {
        start = 0;
        end = 0;
    }

}
