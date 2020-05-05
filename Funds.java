package cs.ia;
import java.util.Date;
public class Funds
{
    private int divisionCode;
    private String dateRequested;
    private String dateArranged;
    private int bankCode;
    private int bankNum;
    private String iban;
    private int code;
    private String currency;
    private double amount;
    private String purpose;
    private boolean complete;
    private double startAmount;
        
    Funds(int co, int dC, String dR, String dA, int bnkCod, int bnkNum, String ibn, String cu, double a, String p, boolean c)
    {
        setDivisionCode(dC);
        setDateRequested(dR);
        setDateArranged(dA);
        setCode(co);
        setCurrency(cu);
        setAmount(a);
        setPurpose(p);
        setBankCode(bnkCod);
        setBankNum(bnkNum);
        setIBAN(ibn);
        setComplete(c);
    }
    
    public void setDivisionCode(int diviCode)
    {
        divisionCode = diviCode;
    }

    public void setDateRequested(String dateR)
    {
        dateRequested = dateR;
    }
    
    public void setDateArranged(String dateA)
    {
        dateArranged = dateA;
    }
    
    public void setCode(int cod)
    {
        code = cod;
    }
    
    public void setCurrency(String cur)
    {
        currency = cur;
    }
    
    public void setAmount(double am)
    {
        amount = am;
    }
    
    public void setPurpose(String pur)
    {
        purpose = pur;
    }
    
    public void setBankCode(int bancC)
    {
        bankCode = bancC;
    }
    
    public void setBankNum(int bancNum)
    {
        bankNum = bancNum;
    }
    
    public void setIBAN(String ipan)
    {
        iban = ipan;
    }
    
    public String setComplete(boolean fin)
    {
        complete = fin;
        if(fin == true)
        {
            return "Yes";
        }
        else
        {
            return "No";
        }
    }
    
    public int getDivisionCode()
    {
        return divisionCode;
    }
    
    public String getDateRequested()
    {
        return dateRequested;
    }
    
    public String getDateArranged()
    {
        return dateArranged;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getCurrency()
    {
        return currency;
    }
    
    public double getAmount()
    {
        return amount;
    }
    
    public String getPurpose()
    {
        return purpose;
    }  
    
    public int getBankCode()
    {
        return bankCode;
    }
    
    public int getBankNum()
    {
        return bankNum;
    }
    
    public String getIBAN()
    {
        return iban;
    }
    
    public String getComplete()
    {
        if(complete == true)
        {
            return "Yes";
        }
        else
        {
            return "No";
        }
    }
    
    void setStartAmount(double x)
    {
        startAmount = x;
    }
    
    double startAmount()
    {
        return startAmount;
    }
    
    public String toString()
    {
        return(divisionCode + "\t\t" + code + "\t\t" + dateRequested + "\t\t" + dateArranged + "\t\t" + bankCode + "\t\t" + bankNum + "\t\t" + iban + "\t\t" + currency + "\t\t" + amount + "\t\t" + purpose + "\t\t" + complete + "\n");
    }    
}