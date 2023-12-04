/**
 * Kelas QRIS adalah implementasi dari interface Pembayaran untuk metode pembayaran menggunakan QRIS.
 * Setiap objek QRIS memiliki identifikasi yang dapat diakses dengan menggunakan metode getId().
 */
class QRIS implements Pembayaran {
    private String id;

    /**
     * Konstruktor untuk membuat objek QRIS dengan identifikasi tertentu.
     *
     * @param id String berisi identifikasi pembayaran QRIS.
     */
    public QRIS(String id) {
        this.id = id;
    }

    /**
     * Metode untuk mendapatkan identifikasi pembayaran QRIS.
     *
     * @return String berisi identifikasi pembayaran QRIS.
     */
    @Override
    public String getId() {
        return id;
    }
}
