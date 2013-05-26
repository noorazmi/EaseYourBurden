package com.project.parsers;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
    private ArrayList<DebitsData> debitsDataList;


    @Override
    public void startDocument() throws SAXException
    {
	debitsDataList = new ArrayList<DebitsData>();
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
	    debitsData = new DebitsData();
	    debitsData.setAmount(attributes.getValue("total").trim());
	}
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
	if (localName.equals("Name"))
	{
	    debitsData.setEmployeeName(buffer.toString().trim());
	    buffer = new StringBuffer();

	}
	else if (localName.equals("Id"))
	{
	    debitsData.setEmployeeId(buffer.toString().trim());
	    buffer = new StringBuffer();
	}
	else if (localName.equals("Type"))
	{
	    debitsData.setType(buffer.toString().trim());
	    buffer = new StringBuffer();
	    debitsDataList.add(debitsData);
	}
	
    }

    public ArrayList<DebitsData> getDebitsList()
    {
	return debitsDataList;
    }
    
}
