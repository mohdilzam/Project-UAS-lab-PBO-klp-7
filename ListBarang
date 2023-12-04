import java.util.ArrayList;
import java.util.List;

/**
 * Kelas yang merepresentasikan daftar barang dalam aplikasi belanja online.
 */
public class ListBarang {
    private List<Barang> listBarang;
    private List<Transaksi> listTransaksi; // Menambahkan daftar untuk menyimpan transaksi

    /**
     * Konstruktor untuk membuat ListBarang baru dengan daftar barang kosong dan daftar transaksi kosong.
     */
    public ListBarang() {
        this.listBarang = new ArrayList<>();
        this.listTransaksi = new ArrayList<>();
    }

    /**
     * Menambahkan barang baru ke dalam daftar.
     *
     * @param barang Barang yang akan ditambahkan.
     */
    public void tambahBarang(Barang barang) {
        listBarang.add(barang);
    }

    /**
     * Menampilkan daftar barang dalam format tabel.
     */
    public void displayAsTable() {
        System.out.printf("+--------+----------------------+-------------+--------+%n");
        System.out.printf("| ID     | Nama Barang          | Harga       | Jumlah |%n");
        System.out.printf("+--------+----------------------+-------------+--------+%n");

        for (Barang barang : listBarang) {
            System.out.printf("| %-6s | %-20s | %-11.2f | %-6d |%n",
                    barang.getId(), barang.getNama(), barang.getHarga(), barang.getJumlah());
        }

        System.out.println("+--------+----------------------+-------------+--------+");
    }

    /**
     * Mendapatkan daftar barang.
     *
     * @return Daftar barang.
     */
    public List<Barang> getBarang() {
        return listBarang;
    }

    /**
     * Menghapus barang dari daftar berdasarkan ID-nya.
     *
     * @param id ID barang yang akan dihapus.
     * @return Barang yang dihapus, atau null jika barang dengan ID yang spesifik tidak ditemukan.
     */
    public Barang hapusBarang(String id) {
        for (Barang barang : listBarang) {
            if (barang.getId().equals(id)) {
                listBarang.remove(barang);
                return barang;
            }
        }
        return null;
    }

    /**
     * Mencari barang dalam daftar berdasarkan ID-nya.
     *
     * @param id ID barang yang akan dicari.
     * @return Barang dengan ID yang spesifik, atau null jika tidak ditemukan.
     */
    public Barang cariBarangById(String id) {
        for (Barang barang : listBarang) {
            if (barang.getId().equals(id)) {
                return barang;
            }
        }
        return null;
    }

    /**
     * Metode placeholder untuk proses checkout.
     * Metode ini mungkin diperluas dengan logika checkout aktual di masa depan.
     */
    public void checkout() {
        // Metode placeholder
    }

    /**
     * Menghapus semua barang dari daftar.
     */
    public void clear() {
        listBarang.clear();
    }

    /**
     * Mendapatkan daftar transaksi.
     *
     * @return Daftar transaksi.
     */
    public List<Transaksi> getTransaksi() {
        return listTransaksi;
    }

    /**
     * Mengatur daftar transaksi.
     *
     * @param transaksiList Daftar transaksi yang akan diatur.
     */
    public void setTransaksi(List<Transaksi> transaksiList) {
        this.listTransaksi = transaksiList;
    }

    /**
     * Mencari transaksi dalam daftar berdasarkan ID-nya.
     *
     * @param idTransaksi ID transaksi yang akan dicari.
     * @return Transaksi dengan ID yang spesifik, atau null jika tidak ditemukan.
     */
    public Transaksi cariTransaksiById(int idTransaksi) {
        for (Transaksi transaksi : listTransaksi) {
            if (transaksi.getId() == idTransaksi) {
                return transaksi;
            }
        }
        return null;
    }
}
