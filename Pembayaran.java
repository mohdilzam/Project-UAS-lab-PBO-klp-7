/**
 * Interface Pembayaran adalah kontrak untuk objek yang dapat digunakan sebagai metode pembayaran.
 * Setiap implementasi Pembayaran harus menyediakan metode getId() untuk mendapatkan identifikasi pembayaran.
 */
interface Pembayaran {
    /**
     * Metode untuk mendapatkan identifikasi pembayaran.
     *
     * @return String berisi identifikasi pembayaran.
     */
    String getId();
}
