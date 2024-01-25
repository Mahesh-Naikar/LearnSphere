package com.learnsphere.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learnsphere.entity.Course;
import com.learnsphere.entity.Users;
import com.learnsphere.services.TrainerService;
import com.learnsphere.services.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;


@Controller
public class PaymentController {
	@Autowired
	UserService usService;
	
	@Autowired
	TrainerService trainerService;
	
	@GetMapping("/payment-success")
	public String paymentSuccess(HttpSession session) {
		//getting email from the session
	    String mail = (String) session.getAttribute("email");
	    
	    Users user = usService.getUser(mail);
	    System.out.println(mail);

	    int courseId = (int) session.getAttribute("courseId");

	    Course course = trainerService.getCourse(courseId);
	    
	  //Important : remove the session attribute for courseId
	    session.removeAttribute("courseId");

	    usService.addCourse(user, course);
	    return "studentHome";
	}


	
	@GetMapping("/payment-failure")
	public String paymentFailure() {
		return "studentHome";
	}

	@SuppressWarnings("finally")
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam int courseId,HttpSession session) {

		//Important : set the session attribute for courseId
		session.setAttribute("courseId", courseId);
		
		Course course = trainerService.getCourse(courseId);
	    int amount = course.getCoursePrice();
		Order order=null;
		try {
			RazorpayClient razorpay=new RazorpayClient("rzp_test_2X62Nr4fUdbs3d", "IAQPVf1p3zMDkIx3uAha0zmF");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");

			order = razorpay.orders.create(orderRequest);

		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		finally {
			return order.toString();
		}
	}	
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_2X62Nr4fUdbs3d", "IAQPVf1p3zMDkIx3uAha0zmF");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "IAQPVf1p3zMDkIx3uAha0zmF");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
