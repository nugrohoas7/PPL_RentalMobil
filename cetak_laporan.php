

<?php
 // var_dump(array($transaksi_data));die();

    $data = array();
    $start=0;
    foreach ($transaksi_data as $transaksi)
    {
        $row= array();
        $row[0]= ++$start;
        $row[1]= $transaksi->KODE_TRANSAKSI;
        $row[2]= $transaksi->ID_USER;
        $row[3]= $transaksi->TGL_ORDER;
        $row[4]= $transaksi->TGL_PEMBAYARAN;
        $row[5]= "RP.".number_format($transaksi->TOTAL_PEMBAYARAN);
        $row[6] = ($transaksi->STATUS_PEMBAYARAN==1)? "Lunas" : "Belum Lunas";
        array_push($data, $row);
    }
// var_dump($data);die();

//mengisi judul dan header tabel
$judul = "Laporan Transaksi Rental Mobil";
date_default_timezone_set('Asia/Jakarta');
$date = date('Y-m-d');
$subjudul = $date;
$header = array(
    array("label"=>"NO", "length"=>10, "align"=>"L"),
    array("label"=>"KODE TRANSAKSI", "length"=>35, "align"=>"L"),
    array("label"=>"ID USER", "length"=>15, "align"=>"L"),
    array("label"=>"TGL ORDER", "length"=>30, "align"=>"L"),
    array("label"=>"TGL PEMBAYARAN", "length"=>30, "align"=>"L"),
    array("label"=>"TOTAL PEMBAYARAN", "length"=>35, "align"=>"L"),
    array("label"=>"STATUS", "length"=>20, "align"=>"L")
);
 
//memanggil fpdf
// ini_set("session.auto_start", 0);
ob_start();
require_once ("fpdf/fpdf.php");
$pdf = new FPDF();
$pdf->AddPage();
 
//tampilan Judul Laporan
$pdf->SetFont('Arial','B','16'); //Font Arial, Tebal/Bold, ukuran font 16
$pdf->Cell(0,0, $judul, '0', 1, 'C');
$pdf->SetFont('Arial','','12'); //Font Arial, Tebal/Bold, ukuran font 12
$pdf->Cell(0,20, $subjudul, '0', 1, 'C');
 
//Header Table
$pdf->SetFont('Arial','','8');
$pdf->SetFillColor(139, 69, 19); //warna dalam kolom header
$pdf->SetTextColor(255); //warna tulisan putih
$pdf->SetDrawColor(222, 184, 135); //warna border
foreach ($header as $kolom) {
    $pdf->Cell($kolom['length'], 5, $kolom['label'], 1, '0', $kolom['align'], true);
}
$pdf->Ln();
 
// menampilkan data table
$pdf->SetFillColor(245, 222, 179); //warna dalam kolom data
$pdf->SetTextColor(0); //warna tulisan hitam
$pdf->SetFont('');
$fill=false;
foreach ($data as $baris) {
    $i = 0;
    foreach ($baris as $cell) {
        $pdf->Cell($header[$i]['length'], 10, $cell, 1, '0', $kolom['align'], $fill);
        $i++;
        // echo $i;
    }
    $fill = !$fill;
    $pdf->Ln();
}
 
//output file pdf
date_default_timezone_set('Asia/Jakarta');
$date = date('Y-m-d');
$filename = "Laporan_RentalMobil_".$date.".pdf";
$pdf->Output($filename,'I');
?>