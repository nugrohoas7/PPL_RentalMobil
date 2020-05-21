<!DOCTYPE html>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Unyu | Rental Mobil</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
    <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/bootstrap/css/bootstrap.min.css">
   <link rel="stylesheet" href="<?php echo base_url('');?>assets/css/bootstrap-datetimepicker.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/font-awesome-4.6.3/css/font-awesome.min.css">
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/font-awesome-4.6.3/css/font-awesome.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/ionicons-2.0.1/css/ionicons.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/datepicker/datepicker3.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/iCheck/all.css">
  <!-- Bootstrap Color Picker -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/colorpicker/bootstrap-colorpicker.min.css">
  <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/timepicker/bootstrap-timepicker.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/select2/select2.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/dist/css/AdminLTE.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/datatables/dataTables.bootstrap.css">
    <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/plugins/datepicker/datepicker3.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<?php echo base_url('');?>assets/dist/css/skins/_all-skins.min.css">
  
  <link rel="stylesheet" href="<?php echo base_url('assets/datatables/dataTables.bootstrap.css') ?>"/>
  <link rel="stylesheet" href="<?php echo base_url('assets/datatables/dataTables.bootstrap.css') ?>"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="<?php echo base_url();?>" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>U</b>RM</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Unyu</b> Rental Mobil</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="<?php echo base_url('/upload/avatars/'.$this->session->userdata('PHOTO'));?>" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs"><?php echo $this->session->userdata("NAME");?></span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="<?php echo base_url('/upload/avatars/'.$this->session->userdata('PHOTO'));?>" class="img-circle" alt="User Image">

                <p>
                  <?php echo $this->session->userdata("NAME");?> - Web Developer
                  <small>Member. 2020</small>
                </p>
              </li>
              <!-- Menu Body -->
              
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                
                <div class="pull-right">
                  <a href="<?php echo site_url("Login/logout");?>" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<?php echo base_url('/upload/avatars/'.$this->session->userdata('PHOTO'));?>" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><?php echo $this->session->userdata("NAME");?></p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">MENU</li>
        <!-- Optionally, you can add icons to the links -->
        <li><a href="<?php echo site_url('mobil');?>"><i class="fa fa-car"></i> <span>Mobil</span></a></li>
        <li><a href="<?php echo site_url('fasilitas');?>"><i class="fa fa-life-ring"></i> <span>Fasilitas Mobil</span></a></li>  
        <li><a href="<?php echo site_url('transaksi');?>"><i class="fa fa-shopping-cart "></i> <span>Pesanan</span></a></li>
        <li><a href="<?php echo site_url('transaksi/proses_list');?>"><i class="fa fa-area-chart"></i> <span>Proses Peminjaman</span></a></li>
        <li><a href="<?php echo site_url('transaksi/selesai_list');?>"><i class="fa fa-check"></i> <span>Transaksi Selesai</span></a></li>
    <!--     <li><a href="<?php echo site_url('users');?>"><i class="fa fa-area-chart"></i> <span>Denda</span></a></li>   -->             
        <li><a href="<?php echo site_url('users');?>"><i class="fa fa-user"></i> <span>Users</span></a></li>               
<!--         <li class="treeview">
          <a href="#"><i class="fa fa-edit"></i> <span>Coustomers</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<?php echo site_url('pembeli');?>"><i class="fa fa-angle-double-right"></i>Form Data Coustomers</a></li>
            <li><a href="<?php echo site_url('pembeli/view_pembeli');?>"><i class="fa fa-angle-double-right"></i>List Coustomers</a></li>
          </ul>
        </li>
        
        <li class="treeview">
          <a href="#"><i class="fa fa-motorcycle"></i> <span>Motor</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<?php echo site_url('motor');?>"><i class="fa fa-angle-double-right"></i>Form Detail Motor</a></li>
            <li><a href="<?php echo site_url('motor/view_list_motor');?>"><i class="fa fa-angle-double-right"></i>List Motor</a></li>
            <li><a href="<?php echo site_url('motor/merk');?>"><i class="fa fa-angle-double-right"></i>Merk Motor</a></li>
            <li><a href="<?php echo site_url('motor/tipe');?>"><i class="fa fa-angle-double-right"></i>Tipe Motor</a></li>
            <li><a href="<?php echo site_url('motor/warna');?>"><i class="fa fa-angle-double-right"></i>Warna Motor</a></li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#"><i class="fa fa-gear"></i> <span>Order Cash</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<?php echo site_url('pembeli_cash');?>"><i class="fa fa-angle-double-right"></i>Form Order Cash</a></li>
            <li><a href="#"><i class="fa fa-angle-double-right"></i>List Order Cash</a></li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#"><i class="fa fa-check"></i> <span>Order Kredit</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<?php echo site_url('pembeli_cash');?>"><i class="fa fa-angle-double-right"></i>Form Order Cash</a></li>
            <li><a href="#"><i class="fa fa-angle-double-right"></i>List Order Cash</a></li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#"><i class="fa fa-building"></i> <span>Paket Kredit</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<?php echo site_url('paket_kredit');?>"><i class="fa fa-angle-double-right"></i>Form Paket Kredit</a></li>
            <li><a href="#"><i class="fa fa-angle-double-right"></i>List Paket Kredit</a></li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#"><i class="fa fa-money"></i> <span>Bayar Cicilan</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<?php echo site_url('pembeli_cash');?>"><i class="fa fa-angle-double-right"></i>Form Order Cash</a></li>
            <li><a href="#"><i class="fa fa-angle-double-right"></i>List Order Cash</a></li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#"><i class="fa fa-user-plus"></i> <span>Karyawan</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<?php echo site_url('karyawan');?>"><i class="fa fa-angle-double-right"></i>Form Detail Karyawan</a></li>
            <li><a href="<?php echo site_url('karyawan/view_karyawan');?>"><i class="fa fa-angle-double-right"></i>List Karyawan</a></li>
            <li><a href="<?php echo site_url('karyawan/posisi');?>"><i class="fa fa-angle-double-right"></i>Posisi Karyawan</a></li>
          </ul>
        </li> -->
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>