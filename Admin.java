/**
 * Kelas Admin merupakan turunan dari kelas Akun dan merepresentasikan akun administrator dalam sistem.
 */
class Admin extends Akun {
    /**
     * Konstruktor untuk membuat objek Admin.
     *
     * @param id   ID unik untuk admin.
     * @param nama Nama admin.
     */
    public Admin(String id, String nama) {
        super(id, nama);
    }

    /**
     * Mengeset metode pembayaran untuk admin.
     *
     * @param pembayaran Objek Pembayaran yang akan dihubungkan dengan admin.
     */
    @Override
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
        // Implementasi untuk Admin
    }
}
