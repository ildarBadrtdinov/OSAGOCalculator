package net.codejava.javaee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/result.html")
public class JavaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		int TB = 4118; //������� ������������ �������� ������ 
		double KT = 0;  //����������� ����������
		double KBM = 1; //����������� �����-�����, �� ��������� 3 (1)
		double KVS = 0; //����������� �������-���� (����������� 
		double KOMultidrive = 1.8; //��� ������������
		double KM = 0; //����������� ��������
		double CalculatePrem; //���������� ��� ������� ��������� ������
		String ResultHtml = "<!DOCTYPE html>  \r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"UTF-8\">\r\n" + 
				"<link rel = \"stylesheet\" href = \"style.css\">\r\n" + 
				"<link href=\"https://fonts.googleapis.com/css?family=Roboto+Slab\" rel=\"stylesheet\">\r\n" + 
				"<title>���������</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" +
				"<div class=forma>";
		PrintWriter out = response.getWriter(); //������� ����� ���������� ������ ��� ������ ������
		String UsageMoscow = request.getParameter("UsageMoscow");  //�������� ������ ������ ��������
		String UsageSPB = request.getParameter("UsageSPB");
		String UsageUfa = request.getParameter("UsageUfa");
		if ((UsageMoscow !=null & UsageSPB == null & UsageUfa == null) || (UsageMoscow ==null & UsageSPB != null & UsageUfa == null) || (UsageMoscow ==null & UsageSPB == null & UsageUfa != null)) //�������� �� ����� ���� �� ������ �������� ������� �����������
			{
			   if (UsageMoscow !=null & UsageSPB == null & UsageUfa == null) KT=2;
			   if (UsageMoscow ==null & UsageSPB != null & UsageUfa == null) KT=1.8;
			   if (UsageMoscow ==null & UsageSPB == null & UsageUfa != null) KT=1.8;
			   int Power = Integer.parseInt(request.getParameter("Power")); //������ ��������� �������� � ���
			   int Age = Integer.parseInt(request.getParameter("Age"));
			   int Experience = Integer.parseInt(request.getParameter("Experience"));
			   if ((Power >0 & Age >0 & Experience >=0) & (Age >= Experience+18)) // �������� �� ���� �������� �������� ��� �������
				{
				if (Power <=50) KM = 0.6;	
				if (Power >50 & Power <=70) KM = 1;
				if (Power >70 & Power<=100) KM = 1.1;
				if (Power >100 & Power<=120) KM = 1.2;
				if (Power >120 & Power<=150) KM = 1.4;
				if (Power >150) KM = 1.6;
				if (Age <=22 & Experience <= 3 ) KVS = 1.8;
				if (Age > 22 & Experience <=3) KVS = 1.7;
				if (Age <22 & Experience >3) KVS = 1.6;	
				if (Age > 22 & Experience >3) KVS = 1;
				CalculatePrem = TB * KT * KBM * KVS * KM; //������ ��������� ������
				CalculatePrem = Math.rint(100.0*CalculatePrem)/100.0; //��������� ��� ����� ����� �������
				out.println(ResultHtml + "<label>��������� �����:  " + CalculatePrem + " ���.</label><h2>�������������� ������������</h2><h3>T� (������� �����) =" + TB + "</h3><h3>KT (����������� ���������� ����������������� �������������) =" + KT + "</h3><h3>K�M (����������� '�����-�����') =" + KBM + "</h3><h3>��� (����������� � ����������� �� �������� � ����� ��������) =" + KVS + "</h3><h3>�� (����������� ����������� ������������ ��) =" + KM + "</h3></div>");
				}
				else
					out.println(ResultHtml + "<h1>������. ����������� ������� �������� ����� �������, ���� ��� �������� ��</h1"); //������� �� ���� ���������� ������
				}
			   else 
				   out.println (ResultHtml + "<h1>������. ���������� ������� ���� �������� ������</h1>"); //������� �� ����� �� ������ �������� ������
			}	
}

