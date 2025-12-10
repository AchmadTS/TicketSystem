package model;

import java.text.DecimalFormat;

public class Penerbangan {
    public int id;
    public String pesawat;
    public String asal;
    public String tujuan;
    public double harga;
    public int bulan;
    public int hari;
    public int tahun;
    public int jam;
    public int menit;
    public int jumlahKursi;
    
    public Penerbangan(int id, String pesawat, String asal, String tujuan, double harga, int hari, int bulan, int tahun, int jam, int menit, int jumlahKursi) {
        this.id = id;
        this.pesawat = pesawat;
        this.asal = asal;
        this.tujuan = tujuan;
        this.harga = harga;
        this.hari = hari;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jam = jam;
        this.menit = menit;
        this.jumlahKursi = jumlahKursi;
    }

    private String namaBulan(int bulan) {
        String namaBln;
        switch (bulan) {
            case 1:
                namaBln = "Januari";
                break;
            case 2:
                namaBln = "Februari";
                break;
            case 3:
                namaBln = "Maret";
                break;
            case 4:
                namaBln = "April";
                break;
            case 5:
                namaBln = "Mei";
                break;
            case 6:
                namaBln = "Juni";
                break;
            case 7:
                namaBln = "Juli";
                break;
            case 8:
                namaBln = "Agustus";
                break;
            case 9:
                namaBln = "September";
                break;
            case 10:
                namaBln = "Oktober";
                break;
            case 11:
                namaBln = "November";
                break;
            case 12:
                namaBln = "Desember";
                break;
            default:
                namaBln = "Bulan tidak valid";
                break;
        }
        return namaBln;
    }

    @Override
    public String toString() {
        String namaBulan = namaBulan(this.bulan);
        DecimalFormat df = new DecimalFormat("#,###");
        String waktu = (jam < 10 ? "0" + jam : jam) + ":" + (menit < 10 ? "0" + menit : menit) + ":00";
        return "ID:" + id +
                " | " + pesawat +
                " | " + asal + " -> " + tujuan +
                " | " + (hari < 10 ? "0" + hari : hari) + " " + namaBulan + " " + tahun + " " + waktu +
                " | Harga: Rp" + df.format(harga) +
                " | Kursi: " + jumlahKursi;
    }
}