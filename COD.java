/**
 * Kelas COD (Cash On Delivery) merupakan implementasi dari interface Pembayaran untuk representasi pembayaran
 * dengan metode Cash On Delivery.
 */
class COD implements Pembayaran {
    /**
     * ID unik untuk metode pembayaran COD.
     */
    private String id;

    /**
     * Konstruktor untuk membuat objek COD dengan ID tertentu.
     *
     * @param id ID unik untuk metode pembayaran COD.
     */
    public COD(String id) {
        this.id = id;
    }

    /**
     * Mendapatkan ID metode pembayaran COD.
     *
     * @return ID metode pembayaran COD.
     */
    @Override
    public String getId() {
        return id;
    }
}
