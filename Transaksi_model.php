<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Transaksi_model extends CI_Model
{

    public $table = 'tb_transaksi';
    public $table_detail = 'tb_detail_transaksi';
    public $table_mobil = 'tb_mobil';
    public $table_user = 'tb_users';
    public $id  = 'KODE_TRANSAKSI';
    public $order = 'DESC';

    function __construct()
    {
        parent::__construct();
    }

    // datatables
    function json() {
        $this->datatables->select('KODE_TRANSAKSI,ID_USER,TGL_ORDER,TOTAL_PEMBAYARAN,TGL_PEMBAYARAN,STATUS_PEMBAYARAN');
        $this->datatables->from('tb_transaksi');
        //add this line for join
        //$this->datatables->join('table2', 'tb_transaksi.field = table2.field');
        $this->datatables->add_column('action', anchor(site_url('transaksi/read/$1'),'Read')." | ".anchor(site_url('transaksi/update/$1'),'Update')." | ".anchor(site_url('transaksi/delete/$1'),'Delete','onclick="javasciprt: return confirm(\'Are You Sure ?\')"'), 'KODE_TRANSAKSI');
        return $this->datatables->generate();
    }

    // get all
    function get_pesanan()
    {
        $this->db->where("STATUS_TRANSAKSI", 0);
        $this->db->order_by($this->id, $this->order);
        return $this->db->get($this->table)->result();
    }

    function get_proses()
    {
        $this->db->where("STATUS_TRANSAKSI", 1);
        $this->db->order_by($this->id, $this->order);
        return $this->db->get($this->table)->result();
    }

    function get_selesai()
    {
        $this->db->where("STATUS_TRANSAKSI", 3);
        $this->db->order_by($this->id, $this->order);
        return $this->db->get($this->table)->result();
    }

    // get data by id
    function get_by_id($id)
    {
        $this->db->where($this->id, $id);
        $this->db->select("*")->from($this->table);
        $this->db->join($this->table_user,$this->table_user.".ID_USER=".$this->table.".ID_USER");
        return $this->db->get()->row();
    }

    // get data by id
    function get_detail_id($id)
    {

        $this->db->where($this->id, $id);
        $this->db->select("*,".$this->table_detail.".HARGA_MOBIL,".$this->table_detail.".STATUS_MOBIL,")->from($this->table_detail);
        $this->db->join($this->table_mobil,$this->table_mobil.".ID_MOBIL=".$this->table_detail.".ID_MOBIL");
        return $this->db->get()->result();
    }


    // get total rows
    function total_rows($q = NULL) {
        $this->db->like('KODE_TRANSAKSI', $q);
	$this->db->or_like('ID_USER', $q);
	$this->db->or_like('TGL_ORDER', $q);
	$this->db->or_like('TOTAL_PEMBAYARAN', $q);
	$this->db->or_like('TGL_PEMBAYARAN', $q);
	$this->db->or_like('BUKTI_PEMBAYARAN', $q);
	$this->db->or_like('STATUS_PEMBAYARAN', $q);
	$this->db->or_like('STATUS_TRANSAKSI', $q);
	$this->db->from($this->table);
        return $this->db->count_all_results();
    }

    // get data with limit and search
    function get_limit_data($limit, $start = 0, $q = NULL) {
        $this->db->order_by($this->id, $this->order);
        $this->db->like('KODE_TRANSAKSI', $q);
	$this->db->or_like('ID_USER', $q);
	$this->db->or_like('TGL_ORDER', $q);
	$this->db->or_like('TOTAL_PEMBAYARAN', $q);
	$this->db->or_like('TGL_PEMBAYARAN', $q);
	$this->db->or_like('BUKTI_PEMBAYARAN', $q);
	$this->db->or_like('STATUS_PEMBAYARAN', $q);
	$this->db->or_like('STATUS_TRANSAKSI', $q);
	$this->db->limit($limit, $start);
        return $this->db->get($this->table)->result();
    }

    // insert data
    function insert($data)
    {
        $this->db->insert($this->table, $data);
    }

    function insert_detail($data)
    {
        $this->db->insert($this->table_detail, $data);
    }

    // update data
    function update($id, $data)
    {
        $this->db->where($this->id, $id);
        $this->db->update($this->table, $data);
    }

    // delete data
    function delete($id)
    {
        $this->db->where($this->id, $id);
        $this->db->delete($this->table);
    }

    function cancel($data,$id){
        $transaksi=$this->db->select("KODE_TRANSAKSI,TOTAL")->from($this->table_detail)->where("KODE_TRANSAKSI",$id)->get()->row();
        $this->db->query("Update tb_mobil set STATUS_SEWA=0 where ID_MOBIL='".$transaksi->ID_MOBIL."'",FALSE);
        $this->db->query("Update tb_transaksi set STATUS_TRANSAKSI=2 where KODE_TRANSAKSI='".$transaksi->KODE_TRANSAKSI."'",FALSE);
        $this->db->where("KODE_TRANSAKSI",$id);
        $this->db->update($this->table_detail,$data);
        return $transaksi->KODE_TRANSAKSI;
    }

    function confirm($data,$id){
        $transaksi=$this->db->select("KODE_TRANSAKSI,TOTAL,ID_MOBIL")->from($this->table_detail)->where("KODE_TRANSAKSI",$id)->get()->row();
        date_default_timezone_set('Asia/Jakarta');
        $date = date('Y-m-d H:i:s');
        $this->db->query("Update tb_mobil set STATUS_SEWA=1 where ID_MOBIL='".$transaksi->ID_MOBIL."'",FALSE);
        $this->db->query("Update tb_transaksi set STATUS_TRANSAKSI=1, STATUS_PEMBAYARAN=1, TGL_PEMBAYARAN='".$date."' where KODE_TRANSAKSI='".$transaksi->KODE_TRANSAKSI."'",FALSE);
        $this->db->where("KODE_TRANSAKSI",$id);
        $this->db->update($this->table_detail,$data);
        return $transaksi->KODE_TRANSAKSI;
    }

    // function transaksi_confirm($data,$id){
    //     $this->db->where("KODE_TRANSAKSI",$id);
    //     $res = $this->db->update($this->table,$data);
    //     var_dump($res);die();
    //     return $id;
    // }

    function selesai($data,$id){
        $transaksi=$this->db->select("KODE_TRANSAKSI,TOTAL,ID_MOBIL,TGL_AKHIR_PENYEWAAN")->from($this->table_detail)->where("KODE_TRANSAKSI",$id)->get()->row();
        //hitung denda
        $to_time = strtotime($data['TGL_PENGEMBALIAN']);
        $from_time = strtotime($transaksi->TGL_AKHIR_PENYEWAAN);
        // $data["DENDA"] = round(abs($to_time - $from_time) / 60,2)*$transaksi->TOTAL*50/100;
        if($to_time>$from_time) 
           $data["DENDA"] = round(abs($to_time - $from_time) / (60*60),2)*($transaksi->TOTAL*50/100);
        else
            $data["DENDA"] = 0;
        $data["TOTAL"] = $transaksi->TOTAL+$data["DENDA"];

        $this->db->query("Update tb_mobil set STATUS_SEWA=0 where ID_MOBIL='".$transaksi->ID_MOBIL."'",FALSE);
        $this->db->query("Update tb_transaksi set STATUS_TRANSAKSI=3, TOTAL_PEMBAYARAN= ". $data["TOTAL"]." where KODE_TRANSAKSI='".$transaksi->KODE_TRANSAKSI."'",FALSE);
        
        $this->db->where("KODE_TRANSAKSI",$id);
        $this->db->update($this->table_detail,$data);
        return $transaksi->KODE_TRANSAKSI;   
    }

    // function transaksi_selesai($data,$id){
    //     $this->db->where("KODE_TRANSAKSI",$id);
    //     $this->db->update($this->table,$data);
    //     return $transaksi->KODE_TRANSAKSI;   
    // }
}
