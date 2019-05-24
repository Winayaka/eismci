export default [
  // ==================================
  //HOME PATHS
  // ==================================
  {
    path: "/",
    name: "Login",
    view: "Login",
    role: "No"
  },
  {
    path: "/manager/error",
    name: "No Authorization",
    view: "Manager/NoAuth",
    role: "Manager"
  },
  {
    path: "/staffadministrative/error",
    name: "No Authorization",
    view: "StaffAdministrative/NoAuth",
    role: "StaffAdministrative"
  },
  {
    path: "/staffexport/error",
    name: "No Authorization",
    view: "StaffExport/NoAuth",
    role: "StaffExport"
  },
  {
    path: "/customer/error",
    name: "No Authorization",
    view: "Customer/NoAuth",
    role: "Customer"
  },

  // ==================================
  // ROLE STAFF EXPORT
  // ==================================
  {
    path: "/staffexport",
    name: "Home",
    view: "StaffExport/Home",
    role: "StaffExport"
  },
  // ====================
  // Document
  // ====================
  {
    path: "/staffexport/document/detail",
    view: "StaffExport/DocumentDetail",
    props: true,
    role: "StaffExport"
  },
  {
    path: "/staffexport/document/add",
    view: "StaffExport/AddDocument",
    props: true,
    role: "StaffExport"
  },
  // ====================
  // Proforma Invoice
  // ====================
  {
    path: "/staffexport/si",
    name: "ShippingInstruction",
    view: "StaffExport/ShippingInstruction",
    role: "StaffExport"
  },
  {
    path: "/staffexport/si/detail",
    name: "Shipping Instruction Detail",
    view: "StaffExport/SIDetail",
    role: "StaffExport"
  },
  {
    path: "/staffexport/si/edit",
    name: "Edit Shipping Instruction",
    view: "StaffExport/EditSI",
    role: "StaffExport"
  },

  // ==================================
  // ROLE CUSTOMER
  // ==================================
  {
    path: "/customer",
    name: "Home",
    view: "Customer/Home",
    role: "Customer"
  },
  // ====================
  // Document
  // ====================
  {
    path: "/customer/document/detail",
    view: "Customer/DocumentDetail",
    props: true,
    role: "Customer"
  },

  // ====================
  // Proforma Invoice
  // ====================
  {
    path: "/customer/pi",
    name: "Proforma Invoice",
    view: "Customer/ProformaInvoice",
    role: "Customer"
  },
  {
    path: "/customer/pi/detail",
    name: "Proforma Invoice Detail",
    view: "Customer/PIDetail",
    role: "Customer"
  },
  {
    path: "/customer/si/detail",
    name: "hipping Instruction Detail",
    view: "Customer/SIDetail",
    role: "Customer"
  },

  // ==================================
  // ROLE STAFF ADMINISTRATIVE
  // ==================================
  {
    path: "/staffadministrative",
    name: "Home",
    view: "StaffAdministrative/Home",
    role: "StaffAdministrative"
  },
  // ====================
  // Customer
  // ====================
  {
    path: "/staffadministrative/customer/",
    name: "Customer",
    view: "StaffAdministrative/Customer",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/customer/add",
    name: "Add Customer",
    view: "StaffAdministrative/AddCustomer",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/customer/detail",
    name: "Customer Detail",
    view: "StaffAdministrative/CustomerDetail",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/customer/edit",
    name: "Edit Customer",
    view: "StaffAdministrative/EditCustomer",
    role: "StaffAdministrative"
  },
  // ====================
  // Proforma Invoice
  // ====================
  {
    path: "/staffadministrative/pi",
    name: "Proforma Invoice",
    view: "StaffAdministrative/ProformaInvoice",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/pi/add",
    name: "Add Proforma Invoice",
    view: "StaffAdministrative/AddPI",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/pi/detail",
    name: "Proforma Invoice Detail",
    view: "StaffAdministrative/PIDetail",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/pi/edit",
    name: "Edit Proforma Invoice",
    view: "StaffAdministrative/EditPI",
    role: "StaffAdministrative"
  },
  // ====================
  // Purchase Order
  // ====================
  {
    path: "/staffadministrative/po",
    name: "Purchase Order",
    view: "StaffAdministrative/PurchaseOrder",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/po/add",
    name: "Add Purchase Order",
    view: "StaffAdministrative/AddPO",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/po/edit/",
    name: "Edit Purchase Order",
    view: "StaffAdministrative/EditPO",
    role: "StaffAdministrative"
  },
  {
    path: "/staffadministrative/po/detail",
    name: "Purchase Order Detail",
    view: "StaffAdministrative/PODetail",
    role: "StaffAdministrative"
  },

  // ==================================
  // ROLE MANAGER
  // ==================================
  {
    path: "/manager",
    view: "Manager/Home",
    role: "Manager"
  },
  // ====================
  // Document
  // ====================
  {
    path: "/manager/document/detail",
    view: "Manager/DocumentDetail",
    props: true,
    role: "Manager"
  },
  {
    path: "/manager/document/add",
    view: "Manager/AddDocument",
    props: true,
    role: "Manager"
  },
  // ====================
  // Proforma Invoice
  // ====================
  {
    path: "/manager/pi",
    name: "Proforma Invoice",
    view: "Manager/ProformaInvoice",
    role: "Manager"
  },
  {
    path: "/manager/pi/detail",
    name: "Proforma Invoice Detail",
    view: "Manager/PIDetail",
    role: "Manager"
  },
  {
    path: "/manager/si/detail",
    name: "Shipping Instruction Detail",
    view: "Manager/SIDetail",
    role: "Manager"
  },
  {
    path: "/manager/si/add",
    name: "Add SI",
    view: "Manager/AddSI",
    role: "Manager"
  },
  {
    path: "/manager/si/edit",
    view: "Manager/EditSI",
    name: "Edit Shipping Instruction",
    role: "Manager"
  },
  {
    path: "/manager/document/add",
    view: "Manager/AddDocument",
    name: "Edit Shipping Instruction",
    role: "Manager"
  },
  // ====================
  // Purchase Order
  // ====================
  {
    path: "/manager/po",
    name: "Purchase Order",
    view: "Manager/PurchaseOrder",
    role: "Manager"
  },
  {
    path: "/manager/po/detail",
    name: "Purchase Order Detail",
    view: "Manager/PODetail",
    role: "Manager"
  },
  // ====================
  // Staff
  // ====================
  {
    path: "/manager/staff",
    name: "Staff",
    view: "Manager/Staff",
    role: "Manager"
  },
  {
    path: "/manager/staff/add",
    name: "Add Staff",
    view: "Manager/AddStaff",
    role: "Manager"
  },
  {
    path: "/manager/staff/detail",
    name: "Staff Detail",
    view: "Manager/StaffDetail",
    role: "Manager"
  },
  {
    path: "/manager/staff/edit",
    name: "Edit Staff",
    view: "Manager/EditStaff",
    role: "Manager"
  },
  // ====================
  // Customer
  // ====================
  {
    path: "/manager/customer",
    name: "Customer",
    view: "Manager/Customer",
    role: "Manager"
  },
  {
    path: "/manager/customer/detail",
    name: "Customer Detail",
    view: "Manager/CustomerDetail",
    role: "Manager"
  },
  // ====================
  // Report
  // ====================
  {
    path: "/manager/report",
    name: "Report",
    view: "Manager/Report",
    role: "Manager"
  },
  {
    path: "/manager/report/transaction",
    name: "Transaction Recapitulation",
    view: "Manager/ReportTransaction",
    role: "Manager"
  },
  {
    path: "/manager/report/priority",
    name: "Priority Transaction",
    view: "Manager/PriorityTransaction",
    role: "Manager"
  },
  {
    path: "/manager/report/priority/add",
    name: "Add Priority Transaction",
    view: "Manager/AddPriorityTransaction",
    role: "Manager"
  },
  // ====================
  // Report
  // ====================
  {
    path: "/manager/pi-po",
    name: "PI-PO",
    view: "Manager/PIPOspecial",
    role: "Manager"
  }
];
