<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Transaksi extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('Transaksi_model');
        $this->load->model('M_mobil_admin');
        $this->load->model('Users_model');
        $this->load->library('form_validation');        
	    $this->load->library('datatables');
    }

    public function index()
    {
        $transaksi = $this->Transaksi_model->get_pesanan();
        $data = array(
            'transaksi_data' => $transaksi,
        );

        $this->load->view('template/header');
        $this->load->view('transaksi/tb_transaksi_list', $data);
        $this->load->view('template/footer');
    } 

    public function proses_list()
    {
        $transaksi = $this->Transaksi_model->get_proses();
        $data = array(
            'transaksi_data' => $transaksi,
        );

        $this->load->view('template/header');
        $this->load->view('transaksi/tb_transaksi_list_proses', $data);
        $this->load->view('template/footer');
    } 

    public function selesai_list()
    {
        $transaksi = $this->Transaksi_model->get_selesai();
        $data = array(
            'transaksi_data' => $transaksi,
        );

        $this->load->view('template/header');
        $this->load->view('transaksi/tb_transaksi_list_selesai', $data);
        $this->load->view('template/footer');
    } 
    
    public function json() {
        header('Content-Type: application/json');
        echo $this->Transaksi_model->json();
    }

    // public function read($id) 
    // {
    //     $row = $this->Transaksi_model->get_by_id($id);
    //     if ($row) {
            
    //         $data = array(
    //     		'KODE_TRANSAKSI' => $row->KODE_TRANSAKSI,
    //     		'ID_USER' => $row->ID_USER,
    //             'NAMA_USER' => $row->NAME,
    //     		'TGL_ORDER' => $row->TGL_ORDER,
    //     		'TOTAL_PEMBAYARAN' => $row->TOTAL_PEMBAYARAN,
    //     		'TGL_PEMBAYARAN' => $row->TGL_PEMBAYARAN,
    //     		'BUKTI_PEMBAYARAN' => $row->BUKTI_PEMBAYARAN,
    //     		'STATUS_PEMBAYARAN' => $row->STATUS_PEMBAYARAN,
    //     		'STATUS_TRANSAKSI' => $row->STATUS_TRANSAKSI,
    //             'DANA_KEMBALI' => $row->DANA_KEMBALI,
    //     	    'DETAIL_TRANSAKSI' => $this->Transaksi_model->get_detail_id($id),
    //         );


    //         $this->load->view('template/header');
    //         $this->load->view('transaksi/tb_transaksi_read', $data);
    //         $this->load->view('template/footer');
    //     } else {
    //         $this->session->set_flashdata('message', 'Record Not Found');
    //         redirect(site_url('transaksi'));
    //     }
    // }

    public function detail_pesanan($id) 
    {
        $row = $this->Transaksi_model->get_by_id($id);
        if ($row) {
            
            $data = array(
                'KODE_TRANSAKSI' => $row->KODE_TRANSAKSI,
                'ID_USER' => $row->ID_USER,
                'NAMA_USER' => $row->NAME,
                'TGL_ORDER' => $row->TGL_ORDER,
             //   'HARGA_MOBIL' => $row->HARGA_MOBIL,
                'TOTAL_PEMBAYARAN' => $row->TOTAL_PEMBAYARAN,
                'TGL_PEMBAYARAN' => $row->TGL_PEMBAYARAN,
                'BUKTI_PEMBAYARAN' => $row->BUKTI_PEMBAYARAN,
                'STATUS_PEMBAYARAN' => $row->STATUS_PEMBAYARAN,
                'STATUS_TRANSAKSI' => $row->STATUS_TRANSAKSI,
                // 'DANA_KEMBALI' => $row->DANA_KEMBALI,
                'DETAIL_TRANSAKSI' => $this->Transaksi_model->get_detail_id($id),
            );


            $this->load->view('template/header');
            $this->load->view('transaksi/tb_transaksi_detail_pesanan', $data);
            $this->load->view('template/footer');
        } else {
            $this->session->set_flashdata('message', 'Record Not Found');
            redirect(site_url('transaksi'));
        }
    }

    public function detail_proses($id) 
    {
        $row = $this->Transaksi_model->get_by_id($id);
        if ($row) {
            
            $data = array(
                'KODE_TRANSAKSI' => $row->KODE_TRANSAKSI,
                'ID_USER' => $row->ID_USER,
                'NAMA_USER' => $row->NAME,
                'TGL_ORDER' => $row->TGL_ORDER,
             //   'HARGA_MOBIL' => $row->HARGA_MOBIL,
                'TOTAL_PEMBAYARAN' => $row->TOTAL_PEMBAYARAN,
                'TGL_PEMBAYARAN' => $row->TGL_PEMBAYARAN,
                'BUKTI_PEMBAYARAN' => $row->BUKTI_PEMBAYARAN,
                'STATUS_PEMBAYARAN' => $row->STATUS_PEMBAYARAN,
                'STATUS_TRANSAKSI' => $row->STATUS_TRANSAKSI,
                // 'DANA_KEMBALI' => $row->DANA_KEMBALI,
                'DETAIL_TRANSAKSI' => $this->Transaksi_model->get_detail_id($id),
            );


            $this->load->view('template/header');
            $this->load->view('transaksi/tb_transaksi_detail_proses', $data);
            $this->load->view('template/footer');
        } else {
            $this->session->set_flashdata('message', 'Record Not Found');
            redirect(site_url('transaksi'));
        }
    }

    public function detail_selesai($id) 
    {
        $row = $this->Transaksi_model->get_by_id($id);
        if ($row) {
            
            $data = array(
                'KODE_TRANSAKSI' => $row->KODE_TRANSAKSI,
                'ID_USER' => $row->ID_USER,
                'NAMA_USER' => $row->NAME,
                'TGL_ORDER' => $row->TGL_ORDER,
             //   'HARGA_MOBIL' => $row->HARGA_MOBIL,
                'TOTAL_PEMBAYARAN' => $row->TOTAL_PEMBAYARAN,
                'TGL_PEMBAYARAN' => $row->TGL_PEMBAYARAN,
                'BUKTI_PEMBAYARAN' => $row->BUKTI_PEMBAYARAN,
                'STATUS_PEMBAYARAN' => $row->STATUS_PEMBAYARAN,
                'STATUS_TRANSAKSI' => $row->STATUS_TRANSAKSI,
                // 'DANA_KEMBALI' => $row->DANA_KEMBALI,
                'DETAIL_TRANSAKSI' => $this->Transaksi_model->get_detail_id($id),
            );


            $this->load->view('template/header');
            $this->load->view('transaksi/tb_transaksi_detail_selesai', $data);
            $this->load->view('template/footer');
        } else {
            $this->session->set_flashdata('message', 'Record Not Found');
            redirect(site_url('transaksi'));
        }
    }

    public function create() 
    {
        $data = array(
            'button' => 'Create',
            'action' => site_url('transaksi/create_action'),
    	    'KODE_TRANSAKSI' => set_value('KODE_TRANSAKSI'),
    	    'ID_USER' => set_value('ID_USER'),
    	    'TGL_ORDER' => set_value('TGL_ORDER'),
            'HARGA_MOBIL' => set_value('HARGA_MOBIL'),
    	    'TOTAL_PEMBAYARAN' => set_value('TOTAL_PEMBAYARAN'),
    	    'TGL_PEMBAYARAN' => set_value('TGL_PEMBAYARAN'),
    	    'BUKTI_PEMBAYARAN' => set_value('BUKTI_PEMBAYARAN'),
    	    'STATUS_PEMBAYARAN' => set_value('STATUS_PEMBAYARAN'),
    	    // 'STATUS_TRANSAKSI' => set_value('STATUS_TRANSAKSI'),
            'STATUS_TRANSAKSI' => 0
    	);
        $data['mobil'] = $this->M_mobil_admin->get_available();
        $this->load->view('template/header');
        $this->load->view('transaksi/tb_transaksi_form', $data);
        $this->load->view('template/footer');
    }
    
    public function create_action() 
    {
        $this->_rules();

        // if ($this->form_validation->run() == FALSE) {
        //     $this->create();
        // } else {
            $data_user = array(
        		'NAME' => $this->input->post('NAMA_USER',TRUE),
        		'NIK' => $this->input->post('NIK',TRUE)	
    	    );
            $userid = $this->Users_model->insert($data_user);
            date_default_timezone_set('Asia/Jakarta');
            $date = date('Y-m-d H:i:s');
            $date2= date('YmdHis');
            $data = array(
                'KODE_TRANSAKSI' => 'TRN-'.$date2,
                'ID_USER' => $userid,
                'TGL_ORDER' => $date,
                'TOTAL_PEMBAYARAN' => $this->input->post('TOTAL_PEMBAYARAN',TRUE),
                'STATUS_PEMBAYARAN' => $this->input->post('STATUS_PEMBAYARAN',TRUE),
                'STATUS_TRANSAKSI' => 0
            );
            $this->Transaksi_model->insert($data);
            $tglSewa = $this->input->post('TGL_SEWA',TRUE);
            $jamSewa = $this->input->post('JAM_SEWA',TRUE);
            $tglSewa = $tglSewa." ".$jamSewa.":00";
            $dateSewa = new DateTime($tglSewa);
            $lamaSewa = $this->input->post('LAMA_PENYEWAAN',TRUE);
            $tglAkhirSewa = $dateSewa->modify('+'.$lamaSewa.' day');
            $tglAkhirSewa =  $tglAkhirSewa->format('Y-m-d H:i:s');

            // var_dump($tglAkhirSewa);die();
            $data_detail = array(
                'KODE_TRANSAKSI' => 'TRN-'.$date2,
                'ID_MOBIL' => $this->input->post('ID_MOBIL',TRUE),
                'STATUS_MOBIL' => 1,
                'HARGA_MOBIL' => $this->input->post('HARGA_MOBIL',TRUE),
                'TGL_SEWA' => $tglSewa,
                'TGL_AKHIR_PENYEWAAN' => $tglAkhirSewa,
                'TOTAL' => $this->input->post('TOTAL_PEMBAYARAN',TRUE),
                'STATUS' => 0
            );
            
            $this->Transaksi_model->insert_detail($data_detail);

            $this->session->set_flashdata('message', 'Create Record Success');
            redirect(site_url('transaksi'));
        // }
    }
    
    public function update($id) 
    {
        $row = $this->Transaksi_model->get_by_id($id);

        if ($row) {
            $data = array(
                'button' => 'Update',
                'action' => site_url('transaksi/update_action'),
        		'KODE_TRANSAKSI' => set_value('KODE_TRANSAKSI', $row->KODE_TRANSAKSI),
        		'ID_USER' => set_value('ID_USER', $row->ID_USER),
        		'TGL_ORDER' => set_value('TGL_ORDER', $row->TGL_ORDER),
        		'TOTAL_PEMBAYARAN' => set_value('TOTAL_PEMBAYARAN', $row->TOTAL_PEMBAYARAN),
        		'TGL_PEMBAYARAN' => set_value('TGL_PEMBAYARAN', $row->TGL_PEMBAYARAN),
        		'BUKTI_PEMBAYARAN' => set_value('BUKTI_PEMBAYARAN', $row->BUKTI_PEMBAYARAN),
        		'STATUS_PEMBAYARAN' => set_value('STATUS_PEMBAYARAN', $row->STATUS_PEMBAYARAN),
        		'STATUS_TRANSAKSI' => set_value('STATUS_TRANSAKSI', $row->STATUS_TRANSAKSI),
	    );
            $this->load->view('template/header');
            $this->load->view('transaksi/tb_transaksi_form', $data);
            $this->load->view('template/footer');
        } else {
            $this->session->set_flashdata('message', 'Record Not Found');
            redirect(site_url('transaksi'));
        }
    }
    
    public function update_action() 
    {
        $this->_rules();

        if ($this->form_validation->run() == FALSE) {
            $this->update($this->input->post('KODE_TRANSAKSI', TRUE));
        } else {
            $data = array(
		'ID_USER' => $this->input->post('ID_USER',TRUE),
		'TGL_ORDER' => $this->input->post('TGL_ORDER',TRUE),
		'TOTAL_PEMBAYARAN' => $this->input->post('TOTAL_PEMBAYARAN',TRUE),
		'TGL_PEMBAYARAN' => $this->input->post('TGL_PEMBAYARAN',TRUE),
		'BUKTI_PEMBAYARAN' => $this->input->post('BUKTI_PEMBAYARAN',TRUE),
		'STATUS_PEMBAYARAN' => $this->input->post('STATUS_PEMBAYARAN',TRUE),
		'STATUS_TRANSAKSI' => $this->input->post('STATUS_TRANSAKSI',TRUE),
	    );

            $this->Transaksi_model->update($this->input->post('KODE_TRANSAKSI', TRUE), $data);
            $this->session->set_flashdata('message', 'Update Record Success');
            redirect(site_url('transaksi'));
        }
    }
    
    public function delete($id) 
    {
        $row = $this->Transaksi_model->get_by_id($id);

        if ($row) {
            $this->Transaksi_model->delete($id);
            $this->session->set_flashdata('message', 'Delete Record Success');
            redirect(site_url('transaksi'));
        } else {
            $this->session->set_flashdata('message', 'Record Not Found');
            redirect(site_url('transaksi'));
        }
    }

    public function _rules() 
    {
	$this->form_validation->set_rules('ID_USER', 'id user', 'trim|required');
	$this->form_validation->set_rules('TGL_ORDER', 'tgl order', 'trim|required');
	$this->form_validation->set_rules('TOTAL_PEMBAYARAN', 'total pembayaran', 'trim|required|numeric');
	$this->form_validation->set_rules('TGL_PEMBAYARAN', 'tgl pembayaran', 'trim|required');
	$this->form_validation->set_rules('BUKTI_PEMBAYARAN', 'bukti pembayaran', 'trim|required');
	$this->form_validation->set_rules('STATUS_PEMBAYARAN', 'status pembayaran', 'trim|required');
	$this->form_validation->set_rules('STATUS_TRANSAKSI', 'status transaksi', 'trim|required');

	$this->form_validation->set_rules('KODE_TRANSAKSI', 'KODE_TRANSAKSI', 'trim');
	$this->form_validation->set_error_delimiters('<span class="text-danger">', '</span>');
    }

    public function cancel($id){
        $data["STATUS"]=2;
        $kode=$this->Transaksi_model->cancel($data,$id);
        redirect(site_url('transaksi'));
    }
    
    public function confirm($id){
        $data["STATUS"]=1;
        $kode=$this->Transaksi_model->confirm($data,$id);
        redirect(site_url('transaksi'));
    }

    public function selesai($id){
        date_default_timezone_set('Asia/Jakarta');
        $date = date('Y-m-d H:i:s');
        // var_dump($date);die();
        $data["STATUS_MOBIL"]=2;
        $data["STATUS"]=3;
        $data["TGL_PENGEMBALIAN"]=$date;
        $kode=$this->Transaksi_model->selesai($data,$id);
        redirect(site_url('transaksi/proses_list'));
    }

    // public function selesai($id){
    //     $data["STATUS_MOBIL"]=2;
    //     $data["STATUS"]=3;
    //     $kode=$this->Transaksi_model->selesai($data,$id);
    //     $data="";
    //     $data["STATUS_TRANSAKSI"]=3;
    //     $kode=$this->Transaksi_model->selesai($data,$id);
    //     redirect(site_url('transaksi/read/'.$kode));
    // }

     public function cetak_laporan(){
        $transaksi = $this->Transaksi_model->get_selesai();
        $data = array(
            'transaksi_data' => $transaksi,
        );
        $this->load->view('transaksi/cetak_laporan', $data);
    }

}
