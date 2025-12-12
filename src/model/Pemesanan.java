package model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import util.Helper;

public class Pemesanan {
    public int idPemesanan;
    public int idPenerbangan;
    public String namaPelanggan;
    public int jumlah;
    public double totalHarga;
    LocalDateTime waktuPesan;

    public Pemesanan(int idPemesanan, int idPenerbangan, String namaPelanggan, int jumlah, double totalHarga, LocalDateTime waktuPesan) {
        this.idPemesanan = idPemesanan;
        this.idPenerbangan = idPenerbangan;
        this.namaPelanggan = namaPelanggan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.waktuPesan = waktuPesan;
    }

    // Return ringkasan pemesanan dalam (string)
    public String ringkasan(Penerbangan penerbangan) {
        String infoPenerbangan = "";
        if (penerbangan != null) {
            String namaBulan = Helper.getNamaBulan(penerbangan.bulan);
            infoPenerbangan = "#" + penerbangan.id + " " + penerbangan.pesawat + " " + penerbangan.asal + " -> " + penerbangan.tujuan + " (" + penerbangan.hari + " " + namaBulan + " " + penerbangan.tahun + ")";
        } else {
            infoPenerbangan = "Penerbangan sudah dihapus";
        }

        String waktu = waktuPesan.getYear() + "-" + Helper.formatDuaDigit(waktuPesan.getMonthValue()) + "-" + Helper.formatDuaDigit(waktuPesan.getDayOfMonth()) + " " + Helper.formatDuaDigit(waktuPesan.getHour()) + ":" + Helper.formatDuaDigit(waktuPesan.getMinute()); // Format waktu pemesanan
        DecimalFormat df = new DecimalFormat("#,###");
        return "ID Pesanan #" + idPemesanan
                + " | Nama: " + namaPelanggan
                + " | Penerbangan: " + infoPenerbangan
                + " | Jumlah: " + jumlah
                + " | Total: Rp" + df.format(totalHarga)
                + " | Waktu Pesan: " + waktu;
    }
}
