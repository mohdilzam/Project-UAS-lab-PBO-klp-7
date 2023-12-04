/**
 * Kelas Barang merepresentasikan suatu barang dalam sistem.
 */
class Barang {
    private String id;
    private String nama;
    private double harga;
    private int jumlah; // Added field for quantity

    /**
     * Konstruktor untuk membuat objek Barang.
     *
     * @param id             ID unik untuk barang.
     * @param nama           Nama barang.
     * @param harga          Harga satu unit barang.
     * @param jumlahBarang   Jumlah barang yang tersedia.
     */
    public Barang(String id, String nama, double harga, int jumlahBarang) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlahBarang; // Default quantity is set to the provided value
    }

    /**
     * Mendapatkan ID barang.
     *
     * @return ID barang.
     */
    public String getId() {
        return id;
    }

    /**
     * Mendapatkan nama barang.
     *
     * @return Nama barang.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Mendapatkan harga satu unit barang.
     *
     * @return Harga barang.
     */
    public double getHarga() {
        return harga;
    }

    /**
     * Mengubah nama barang.
     *
     * @param nama Nama baru untuk barang.
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Mengubah harga satu unit barang.
     *
     * @param harga Harga baru untuk barang.
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }

    /**
     * Mengubah jumlah barang yang tersedia.
     *
     * @param jumlah Jumlah baru untuk barang.
     */
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    /**
     * Mendapatkan jumlah barang yang tersedia.
     *
     * @return Jumlah barang.
     */
    public int getJumlah() {
        return jumlah;
    }

    /**
     * Representasi string dari objek Barang.
     *
     * @return String yang berisi informasi mengenai barang.
     */
    @Override
    public String toString() {
        return "Barang{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", harga=" + harga +
                ", jumlah=" + jumlah +
                '}';
    }
}
