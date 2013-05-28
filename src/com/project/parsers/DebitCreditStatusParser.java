package com.project.parsers;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.project.parsers.data.DebitsCreditsStatusData;
import com.project.parsers.data.DebitsData;

public class DebitCreditStatusParser extends DefaultHandler
{

    /**
     * Temporary string buffer object to collect the characters read by this
     * parser and store at the end of the element in an appropriate variable.
     */
    private StringBuffer buffer = new StringBuffer();

    private DebitsData debitsData;


    /**
     * List of all employees parsed by this parser.
     */
    private ArrayList<DebitsData> dataList;

    private DebitsCreditsStatusData debitsCreditsStatusData;

    @Override
    public void startDocument() throws SAXException
    {
	debitsCreditsStatusData = new DebitsCreditsStatusData();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
	buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
	if (localName.equals("Debit"))
	{
	    dataList = new ArrayList<DebitsData>();
	    debitsCreditsStatusData.setTotalDebitAmount(attributes.getValue("total").trim());
	}
	else if(localName.equals("Credit"))
	{
	    dataList = new ArrayList<DebitsData>();
	    debitsCreditsStatusData.setTotalCreditAmount(attributes.getValue("total").trim());
	}
	else if(localName.equals("Status"))
	{
	    dataList = new ArrayList<DebitsData>();
	    debitsCreditsStatusData.setTotalStatusAmount(attributes.getValue("total").trim());
	    debitsCreditsStatusData.setStatusType(attributes.getValue("statusType").trim());
	}
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
	if (localName.equals("Name"))
	{
	    debitsData = new DebitsData();
	    debitsData.setEmployeeName(buffer.toString().trim());
	    buffer = new StringBuffer();

	}
	else if (localName.equals("Id"))
	{
	    debitsData.setEmployeeId(buffer.toString().trim());
	    buffer = new StringBuffer();
	}
	else if(localName.equals("Amount"))
	{
	    debitsData.setAmount(buffer.toString().trim());
	    buffer = new StringBuffer();
	}
	else if (localName.equals("Type"))
	{
	    debitsData.setType(buffer.toString().trim());
	    buffer = new StringBuffer();
	    dataList.add(debitsData);
	}
	else if (localName.equals("Debit"))
	{
	    debitsCreditsStatusData.setDebitsDataList(dataList);
	   
	}
	else if (localName.equals("Credit"))
	{
	    debitsCreditsStatusData.setCreditsDataList(dataList);
	}
	else if (localName.equals("Status"))
	{
	    debitsCreditsStatusData.setStatusDataList(dataList);
	}
	
	

    }

    public DebitsCreditsStatusData getDebitsCreditsStatusData()
    {
	return debitsCreditsStatusData;
    }

}
