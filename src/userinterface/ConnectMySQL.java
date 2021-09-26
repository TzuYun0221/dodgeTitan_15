package userinterface;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectMySQL
{
	String url = "jdbc:mysql://localhost:3306/score?";
	String user = "root";
	String pw = "12345678";
	String sql;
	Connection conn;
	PreparedStatement ps;
	Properties properties = new Properties();

	public void record() throws Exception
	{
		if (GameScreen.finish == false)
		{
			int s = GameScreen.score;
			String name = Gameoverpanel.name;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver OK!");

				conn = DriverManager.getConnection(url, user, pw);

				ps = conn.prepareStatement("INSERT INTO dinoscore (name, score) VALUES(?, ?);");
				ps.setString(1, name);
				ps.setInt(2, s);
				ps.execute();
			} catch (SQLException e)
			{
				System.out.println("MYSQL�ާ@����");
				e.printStackTrace();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	String getScore()
	{ // ���G��iList�����s�n
		String showScore = "";

		sql = "SELECT * FROM `dinoscore` order by score DESC";
		try
		{
			conn = DriverManager.getConnection(url, user, pw);
			Statement st = conn.createStatement();
			// ������G��o
			ResultSet result = st.executeQuery(sql);
			// ��ܥX������G
			while (result.next())
			{
				String addScore = ("�W�r : " + result.getString(1) + "\n���� : " + result.getInt(2) + "\n"
						+ "------------------" + "\n");
				showScore = showScore + addScore;
				/*
				 * System.out.println("�W�r : " + result.getString(1)); System.out.println();
				 * System.out.println("���� : " + result.getInt(2)); System.out.println();
				 */

				// �����s��
				/*
				 * result.close(); st.close(); conn.close();
				 */
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return showScore;

	}
}