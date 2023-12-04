import java.util.*;

/**
 * Kelas Bank merupakan implementasi dari interface Pembayaran untuk representasi pembayaran melalui bank.
 */
class Bank implements Pembayaran {
    /**
     * ID unik untuk bank.
     */
    private String id;

    /**
     * Konstruktor untuk membuat objek Bank dengan ID tertentu.
     *
     * @param id ID unik untuk bank.
     */
    public Bank(String id) {
        this.id = id;
    }

    /**
     * Mendapatkan ID bank.
     *
     * @return ID bank.
     */
    @Override
    public String getId() {
        return id;
    }
}
