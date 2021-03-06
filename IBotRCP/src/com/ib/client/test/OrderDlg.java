/*
 * OrderDlg.java
 *
 */
package com.ib.client.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ib.client.ComboLeg;
import com.ib.client.Contract;
import com.ib.client.Order;

public class OrderDlg extends JDialog {
	final static String ALL_GENERIC_TICK_TAGS = "100,101,104,106,162,165,221,225,236";
    final static int OPERATION_INSERT = 0;
    final static int OPERATION_UPDATE = 1;
    final static int OPERATION_DELETE = 2;

    final static int SIDE_ASK = 0;
    final static int SIDE_BID = 1;

    public boolean 		m_rc;
    public int 			m_id;
    public String       m_backfillEndTime;
    public String		m_backfillDuration;
    public String       m_barSizeSetting;
    public int          m_useRTH;
    public int          m_formatDate;
    public int          m_marketDepthRows;
    public String       m_whatToShow;
    public Contract 	m_contract = new Contract();
    public Order 		m_order = new Order();
    public int          m_exerciseAction;
    public int          m_exerciseQuantity;
    public int          m_override;

    private JTextField	m_Id = new JTextField( "0");
    private JTextField	m_BackfillEndTime = new JTextField(22);
    private JTextField	m_BackfillDuration = new JTextField( "1 M");
    private JTextField  m_BarSizeSetting = new JTextField("1 day");
    private JTextField	m_UseRTH = new JTextField( "1");
    private JTextField	m_FormatDate = new JTextField( "1");
    private JTextField	m_WhatToShow = new JTextField( "TRADES");
    private JTextField 	m_symbol = new JTextField( "QQQQ");
    private JTextField 	m_secType = new JTextField( "STK");
    private JTextField 	m_expiry = new JTextField();
    private JTextField 	m_strike = new JTextField( "0");
    private JTextField 	m_right = new JTextField();
    private JTextField 	m_multiplier = new JTextField("");
    private JTextField 	m_exchange = new JTextField( "SMART");
    private JTextField 	m_primaryExch = new JTextField( "ISLAND" );
    private JTextField 	m_currency = new JTextField("USD");
    private JTextField 	m_localSymbol = new JTextField();
    private JTextField 	m_includeExpired = new JTextField("0");
    private JTextField 	m_action = new JTextField( "BUY");
    private JTextField 	m_totalQuantity = new JTextField( "10");
    private JTextField 	m_orderType = new JTextField( "LMT");
    private JTextField 	m_lmtPrice = new JTextField( "40");
    private JTextField 	m_auxPrice = new JTextField( "0");
    private JTextField 	m_goodAfterTime = new JTextField();
    private JTextField 	m_goodTillDate = new JTextField();
    private JTextField 	m_marketDepthRowTextField = new JTextField( "20");
    private JTextField  m_genericTicksTextField = new JTextField(ALL_GENERIC_TICK_TAGS);
    private JTextField m_exerciseActionTextField = new JTextField("1");
    private JTextField m_exerciseQuantityTextField = new JTextField("1");
    private JTextField m_overrideTextField = new JTextField("0");

    private JButton	    m_sharesAlloc = new JButton("FA Allocation Info...");
    private JButton 	m_comboLegs = new JButton( "Add Combination Order Legs...");
    private JButton 	m_ok = new JButton( "OK");
    private JButton 	m_cancel = new JButton( "Cancel");
    private SampleFrame m_parent;

    private String	m_sharesAllocProfile;

    private String      m_faGroup;
    private String      m_faProfile;
    private String      m_faMethod;
    private String      m_faPercentage;
	public  String      m_genericTicks;

    private static final int COL1_WIDTH = 30 ;
    private static final int COL2_WIDTH = 100 - COL1_WIDTH ;
	public static boolean maintainComboLegs = false;
    public void faGroup(String s) { m_faGroup = s;}
    public void faProfile(String s) { m_faProfile = s;}
    public void faMethod(String s) { m_faMethod = s;}
    public void faPercentage(String s) { m_faPercentage = s; }

    private static void addGBComponent(IBGridBagPanel panel, Component comp,
                                       GridBagConstraints gbc, int weightx, int gridwidth)
    {
      gbc.weightx = weightx;
      gbc.gridwidth = gridwidth;
      panel.setConstraints(comp, gbc);
      panel.add(comp, gbc);
    }

    public OrderDlg( SampleFrame owner) {
        super( owner, true);

        m_parent = owner;
        setTitle( "Test");

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints() ;
        gbc.fill = GridBagConstraints.BOTH ;
        gbc.anchor = GridBagConstraints.CENTER ;
        gbc.weighty = 100 ;
        gbc.fill = GridBagConstraints.BOTH ;
        gbc.gridheight = 1 ;
        // create id panel
        IBGridBagPanel pId = new IBGridBagPanel();
        pId.setBorder( BorderFactory.createTitledBorder( "Message Id") );

        addGBComponent(pId, new JLabel( "Id"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pId, m_Id, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;

        // create contract panel
        IBGridBagPanel pContractDetails = new IBGridBagPanel();
        pContractDetails.setBorder( BorderFactory.createTitledBorder( "Contract Info") );
        addGBComponent(pContractDetails, new JLabel( "Symbol"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_symbol, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Security Type"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_secType, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Expiry"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_expiry, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Strike"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_strike, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Put/Call"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_right, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Option Multiplier"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_multiplier, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Exchange"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_exchange, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Primary Exchange"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_primaryExch, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Currency"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_currency, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Local Symbol"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_localSymbol, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pContractDetails, new JLabel( "Include Expired"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pContractDetails, m_includeExpired, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);

        // create order panel
        IBGridBagPanel pOrderDetails = new IBGridBagPanel();
        pOrderDetails.setBorder( BorderFactory.createTitledBorder( "Order Info") );
        addGBComponent(pOrderDetails, new JLabel( "Action"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pOrderDetails, m_action, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pOrderDetails, new JLabel( "Total Order Size"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pOrderDetails, m_totalQuantity, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pOrderDetails, new JLabel( "Order Type"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pOrderDetails, m_orderType, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pOrderDetails, new JLabel( "Lmt Price"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pOrderDetails, m_lmtPrice, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pOrderDetails, new JLabel( "Aux Price"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pOrderDetails, m_auxPrice, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pOrderDetails, new JLabel( "Good After Time"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pOrderDetails, m_goodAfterTime, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);
        addGBComponent(pOrderDetails, new JLabel( "Good Till Date"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE );
        addGBComponent(pOrderDetails, m_goodTillDate, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER);

        // create marketDepth panel
        IBGridBagPanel pMarketDepth = new IBGridBagPanel();
        pMarketDepth.setBorder( BorderFactory.createTitledBorder( "Market Depth") );
        addGBComponent(pMarketDepth, new JLabel( "Number of Rows"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pMarketDepth, m_marketDepthRowTextField, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;

        // create marketData panel
        IBGridBagPanel pMarketData = new IBGridBagPanel();
        pMarketData.setBorder( BorderFactory.createTitledBorder( "Market Data") );
        addGBComponent(pMarketData, new JLabel( "Generic Tick Tags"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pMarketData, m_genericTicksTextField, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;

        // create options exercise panel
        IBGridBagPanel pOptionsExercise= new IBGridBagPanel();
        pOptionsExercise.setBorder( BorderFactory.createTitledBorder( "Options Exercise") );
        addGBComponent(pOptionsExercise, new JLabel( "Action (1 or 2)"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pOptionsExercise, m_exerciseActionTextField, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;
        addGBComponent(pOptionsExercise, new JLabel( "Number of Contracts"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pOptionsExercise, m_exerciseQuantityTextField, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;
        addGBComponent(pOptionsExercise, new JLabel( "Override (0 or 1)"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pOptionsExercise, m_overrideTextField, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;

        // create historical data panel
        IBGridBagPanel pBackfill = new IBGridBagPanel();
        pBackfill.setBorder( BorderFactory.createTitledBorder( "Historical Data Query") );
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateTime = "" +
            gc.get(Calendar.YEAR) +
            pad(gc.get(Calendar.MONTH) + 1) +
            pad(gc.get(Calendar.DAY_OF_MONTH)) + " " +
            pad(gc.get(Calendar.HOUR_OF_DAY)) + ":" +
            pad(gc.get(Calendar.MINUTE)) + ":" +
            pad(gc.get(Calendar.SECOND)) + " " +
            gc.getTimeZone().getDisplayName( false, TimeZone.SHORT);

        m_BackfillEndTime.setText(dateTime);
        addGBComponent(pBackfill, new JLabel( "End Date/Time"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pBackfill, m_BackfillEndTime, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;
        addGBComponent(pBackfill, new JLabel( "Duration"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pBackfill, m_BackfillDuration, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;
        addGBComponent(pBackfill, new JLabel( "Bar Size Setting (1 to 11)"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pBackfill, m_BarSizeSetting, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;
        addGBComponent(pBackfill, new JLabel( "What to Show"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pBackfill, m_WhatToShow, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;
        addGBComponent(pBackfill, new JLabel( "Regular Trading Hours (1 or 0)"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pBackfill, m_UseRTH, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;
        addGBComponent(pBackfill, new JLabel( "Date Format Style (1 or 2)"), gbc, COL1_WIDTH, GridBagConstraints.RELATIVE) ;
        addGBComponent(pBackfill, m_FormatDate, gbc, COL2_WIDTH, GridBagConstraints.REMAINDER) ;

        // create mid Panel
        JPanel pMidPanel = new JPanel();
        pMidPanel.setLayout( new BoxLayout( pMidPanel, BoxLayout.Y_AXIS) );
        pMidPanel.add( pContractDetails, BorderLayout.CENTER);
        pMidPanel.add( pOrderDetails, BorderLayout.CENTER);
        pMidPanel.add( pMarketDepth, BorderLayout.CENTER);
        pMidPanel.add( pMarketData, BorderLayout.CENTER);
        pMidPanel.add( pOptionsExercise, BorderLayout.CENTER);
        pMidPanel.add( pBackfill, BorderLayout.CENTER);
        pMidPanel.add( m_sharesAlloc, BorderLayout.CENTER);
        pMidPanel.add( m_comboLegs, BorderLayout.CENTER);

        // create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add( m_ok);
        buttonPanel.add( m_cancel);

        // create action listeners
        m_sharesAlloc.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onSharesAlloc();
            }
        });

        m_comboLegs.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onAddComboLegs();
            }
        });
        m_ok.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onOk();
            }
        });
        m_cancel.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onCancel();
            }
        });

        // create top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BoxLayout( topPanel, BoxLayout.Y_AXIS) );
        topPanel.add( pId);
        topPanel.add( pMidPanel);

        // create dlg box
        getContentPane().add( topPanel, BorderLayout.CENTER);
        getContentPane().add( buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    private static String pad( int val) {
        return val < 10 ? "0" + val : "" + val;
    }

    void onSharesAlloc() {
        if ( !m_parent.m_bIsFAAccount ) {
            return;
        }

        //SharesAllocDlg dlg = new SharesAllocDlg( this, m_parent.m_FAAcctCodes);
        FAAllocationInfoDlg dlg = new FAAllocationInfoDlg(this);

        // show the combo leg dialog
        dlg.show();
       // m_sharesAllocProfile = dlg.m_rc ? dlg.m_sharesAllocation : "";
    }

    void onAddComboLegs() {
        ComboLegDlg m_comboLegDlg = new ComboLegDlg( this);

    	if (maintainComboLegs) {
        	ComboLegModel comboLegModel = m_comboLegDlg.comboLegModel();
        	comboLegModel.reset();
        	for (int ctr=0; ctr < m_contract.m_comboLegs.size(); ctr++) {
        		ComboLeg comboLeg = (ComboLeg)m_contract.m_comboLegs.get(ctr);
        		comboLegModel.addComboLeg(comboLeg);
        	}
        } else {
            // Flush the old combo legs, if any
        	m_contract.m_comboLegs.removeAllElements();
        }

        // show the combo leg dialog
        m_comboLegDlg.show();
    }

    void onOk() {
        m_rc = false;

        try {
            // set id
            m_id = Integer.parseInt( m_Id.getText() );

            // set contract fields
            m_contract.m_symbol = m_symbol.getText();
            m_contract.m_secType = m_secType.getText();
            m_contract.m_expiry = m_expiry.getText();
            try {
            	m_contract.m_strike = Double.parseDouble( m_strike.getText() );
            }
            catch (NumberFormatException ex) {
            	m_contract.m_strike = 0.0;
            }
            m_contract.m_right = m_right.getText();
            m_contract.m_multiplier = m_multiplier.getText();
            m_contract.m_exchange = m_exchange.getText();
            m_contract.m_primaryExch = m_primaryExch.getText();
            m_contract.m_currency = m_currency.getText();
            m_contract.m_localSymbol = m_localSymbol.getText();
            try {
            	int includeExpired = Integer.parseInt(m_includeExpired.getText());
            	m_contract.m_includeExpired = (includeExpired == 1);
            }
            catch (NumberFormatException ex) {
            	m_contract.m_includeExpired = false;
            }
            
            // set order fields
            m_order.m_action = m_action.getText();
            m_order.m_totalQuantity = Integer.parseInt( m_totalQuantity.getText() );
            m_order.m_orderType = m_orderType.getText();
            m_order.m_lmtPrice = Double.parseDouble( m_lmtPrice.getText() );
            m_order.m_auxPrice = Double.parseDouble( m_auxPrice.getText() );
            m_order.m_sharesAllocation = m_sharesAllocProfile;
            m_order.m_goodAfterTime = m_goodAfterTime.getText();
            m_order.m_goodTillDate = m_goodTillDate.getText();

            m_order.m_faGroup = m_faGroup;
            m_order.m_faProfile = m_faProfile;
            m_order.m_faMethod = m_faMethod;
            m_order.m_faPercentage = m_faPercentage;

            // set historical data fields
            m_backfillEndTime = m_BackfillEndTime.getText();
            m_backfillDuration = m_BackfillDuration.getText();
            m_barSizeSetting = m_BarSizeSetting.getText();
            m_useRTH = Integer.parseInt( m_UseRTH.getText() );
            m_whatToShow = m_WhatToShow.getText();
            m_formatDate = Integer.parseInt( m_FormatDate.getText() );
            m_exerciseAction = Integer.parseInt( m_exerciseActionTextField.getText() );
            m_exerciseQuantity = Integer.parseInt( m_exerciseQuantityTextField.getText() );
            m_override = Integer.parseInt( m_overrideTextField.getText() );;

            // set market depth rows
            m_marketDepthRows = Integer.parseInt( m_marketDepthRowTextField.getText() );
            m_genericTicks = m_genericTicksTextField.getText();
        }
        catch( Exception e) {
            Main.inform( this, "Error - " + e);
            return;
        }

        m_rc = true;
        setVisible( false);
    }

    void onCancel() {
        m_rc = false;
        setVisible( false);
    }

    public void show() {
        m_rc = false;
        super.show();
    }

    void setIdAtLeast( int id) {
        try {
            // set id field to at least id
            int curId = Integer.parseInt( m_Id.getText() );
            if( curId < id) {
                m_Id.setText( String.valueOf( id) );
            }
        }
        catch( Exception e) {
            Main.inform( this, "Error - " + e);
        }
    }
}
