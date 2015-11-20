package customPackage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Item;
import model.Priority;
import model.Status;
import model.Tasker;

/**
 * Servlet implementation class superServlet
 */
@WebServlet("/superServlet")
public class superServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public superServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("registerJSP"))
			processRegisterJSP(request, response);
		else if (action.equals("register"))
			processRegister(request, response);
		else if (action.equals("login"))
			processLogin(request, response);
		else if (action.equals("details"))
			processItemDetails(request, response);
		else if (action.equals("homepage"))
			processHomepage(request, response);
		else if (action.equals("logout"))
			processLogout(request, response);
		else if (action.equals("start"))
			processStart(request, response);
		else if (action.equals("completed"))
			processCompleted(request, response);
		else if (action.equals("change"))
			try {
				processDueDate(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else if (action.equals("add"))
			processAddTask(request, response);
		else if (action.equals("changeStatus"))
			processStatus(request, response);
		else if (action.equals("reports"))
			processReports(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// to direct user to register form page
	private void processRegisterJSP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/register.jsp").forward(
				request, response);

	}

	private void processRegister(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Tasker newTasker = new Tasker();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		if (!TaskersDB.usernameExists(username)) {
			if (!TaskersDB.emailExists(email)) {

				newTasker.setName(name);
				newTasker.setUsername(username);
				newTasker.setPassword(password);
				newTasker.setEmail(email);

				TaskersDB.insert(newTasker);

				newTasker = TaskersDB.selectTaskerByUsername(username);

				session.setAttribute("tasker", newTasker);
				processHomepage(request, response);

			} else {
				String emailError = "Email Already Registered!";
				request.setAttribute("emailError", emailError);
				getServletContext().getRequestDispatcher("/register.jsp")
						.forward(request, response);

			}
		} else {

			String message = "Username Already Exists.";

			request.setAttribute("message", message);

			getServletContext().getRequestDispatcher("/register.jsp").forward(
					request, response);
		}

	}

	private void processLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Tasker taskerUser = TaskersDB.selectATasker(username, password);

		if (taskerUser == null) {
			String message = "Invalid Username and/or Password.";

			session.setAttribute("message", message);

			getServletContext().getRequestDispatcher("/index.jsp").forward(
					request, response);
		}

		else {
			session.setAttribute("tasker", taskerUser);
			processHomepage(request, response);
		}

	}

	private void processHomepage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Item> itemList = ItemsDB.getItems();
		HttpSession session = request.getSession();
		Tasker tasker = (Tasker) session.getAttribute("tasker");
		String taskerid = tasker.getTaskerId();

		ArrayList<Item> items = new ArrayList<Item>();
		int j = 0;
		System.out.println(itemList.size());
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTasker().getTaskerId().equals(taskerid)) {
				items.add(j, itemList.get(i));
				j++;
			}
		}
		request.setAttribute("itemList", items);
		getServletContext().getRequestDispatcher("/homepage.jsp").forward(
				request, response);

	}

	
	private void processReports(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Item> itemList = ItemsDB.getItems();
		HttpSession session = request.getSession();
		Tasker tasker = (Tasker) session.getAttribute("tasker");
		String taskerid = tasker.getTaskerId();

		ArrayList<Item> items = new ArrayList<Item>();
		int j = 0;
		System.out.println(itemList.size());
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getTasker().getTaskerId().equals(taskerid)) {
				items.add(j, itemList.get(i));
				j++;
			}
		}
		request.setAttribute("itemList", items);
		getServletContext().getRequestDispatcher("/reports.jsp").forward(
				request, response);

	}
	private void processLogout(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();

		Tasker tasker = (Tasker) session.getAttribute("tasker");

		session.removeAttribute("tasker");

		try {

			getServletContext().getRequestDispatcher("/index.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void processItemDetails(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String item_id = request.getParameter("item_id");

		Item myItem = new Item();
		myItem = ItemsDB.getItem(item_id);

		if (myItem == null)
			;

		else {
			request.setAttribute("myItem", myItem);

		}

		getServletContext().getRequestDispatcher("/itemDetails.jsp").forward(
				request, response);

	}

	private void processStart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String item_id = request.getParameter("item_id");

		Item myItem = new Item();
		myItem = ItemsDB.getItem(item_id);

		if (myItem == null)
			;

		else {
			Date d = new Date();
			myItem.setDateStarted(d);
			ItemsDB.update(myItem);
			request.setAttribute("myItem", myItem);

		}

		getServletContext().getRequestDispatcher("/itemDetails.jsp").forward(
				request, response);

	}

	private void processCompleted(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String item_id = request.getParameter("item_id");

		Item myItem = new Item();
		myItem = ItemsDB.getItem(item_id);

		if (myItem == null)
			;

		else {
			Date d = new Date();
			myItem.setDateCompleted(d);
			ItemsDB.update(myItem);
			request.setAttribute("myItem", myItem);

		}

		getServletContext().getRequestDispatcher("/itemDetails.jsp").forward(
				request, response);

	}

	private void processDueDate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {

		String item_id = request.getParameter("item_id");
		String newDate = request.getParameter("newDate");

		Item myItem = new Item();
		myItem = ItemsDB.getItem(item_id);

		if (myItem == null)
			;

		else {

			SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMDD");
			Date date = new Date();
			try {
			date = formatter.parse(request.getParameter("newDate"));
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			myItem.setDueDate(date);
			ItemsDB.update(myItem);
			request.setAttribute("myItem", myItem);

		}

		getServletContext().getRequestDispatcher("/itemDetails.jsp").forward(
				request, response);

	}

	private void processAddTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String dueDate =request.getParameter("dueDate");
		System.out.println("Due daye is " + dueDate);
		String description = request.getParameter("description");
		String status = request.getParameter("status");
		String priority = request.getParameter("priority");
		String startDate = request.getParameter("startDate");
		String completedDate = request.getParameter("completedDate");
	
		HttpSession session = request.getSession();
		Tasker tasker = (Tasker) session.getAttribute("tasker");

		SimpleDateFormat dt = new SimpleDateFormat("YYYYMMDD");

		Item newItem = new Item();

		newItem.setTasker(tasker);

		newItem.setDescription(description);
		newItem.setTasker(tasker);
		try {
			Date d1 = dt.parse("2017-11-25");
			System.out.println(d1);
			newItem.setDueDate(d1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (startDate != null) {
			try {
				Date d2 = dt.parse(startDate);
				newItem.setDateStarted(d2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (completedDate != null) {
			try {
				Date d3 = dt.parse(completedDate);
				newItem.setDateCompleted(d3);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Status s = StatusDB.selectStatus(status);
		newItem.setStatus(s);

		Priority p = PriorityDB.selectPriority(priority);
		newItem.setPriority(p);

		ItemsDB.insert(newItem);
		//session.setAttribute("tasker", "tasker");

		getServletContext().getRequestDispatcher("/superServlet?action=homepage").forward(
				request, response);

	}
	
	private void processStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String item_id = request.getParameter("item_id");
		String status = request.getParameter("status");

		Item myItem = new Item();
		myItem = ItemsDB.getItem(item_id);

		if (myItem == null)
			;

		else {

			Status s = StatusDB.selectStatus(status);
			myItem.setStatus(s);
			ItemsDB.update(myItem);
			request.setAttribute("myItem", myItem);

		}

		getServletContext().getRequestDispatcher("/itemDetails.jsp").forward(
				request, response);

	}
	

}
