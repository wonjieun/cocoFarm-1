package cocoFarm.controller;

import java.awt.Window;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cocoFarm.dto.Auction;
import cocoFarm.dto.Auction_Inquire;
import cocoFarm.dto.BidDto;
import cocoFarm.service.Auction_Service;
import cocoFarm.util.Auction_Paging;

@Controller
public class Auction_Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Auction_Controller.class);
	
	@Autowired Auction_Service auctionService;
	
//	==================================경매등록=============================================
	/*임시로 로그인 세션 처리 idx=1*/
	@RequestMapping(value="/auction/auction_register.do", method=RequestMethod.GET)
	public void register() {}
		
	@RequestMapping(value="/auction/auction_register.do", method=RequestMethod.POST)
	public String registerProcess(Auction auction, HttpSession session) {
		/*로그인 세션에서 ID 받아와서 writter_idx에 넣기*/
//		session.setAttribute("idx", 1);
//		System.out.println(session.getAttribute("idx"));
//		auction.setWritter_idx(session.getAttribute("idx"));
		auction.setWritter_idx(Integer.parseInt((String) session.getAttribute("idx")));
		auctionService.write(auction);
		System.out.println(auction);
		
		
		return "redirect:/auction/auction_list.do";
	}
	
//	==================================경매리스트=============================================
	@RequestMapping(value="/auction/auction_list.do"
			, method=RequestMethod.GET)
	public String list(
			@RequestParam(defaultValue="0") int curPage
			, Auction_Paging search
			, Model model) {
		
//		int totalCount = auctionService.getTotal();	//전체 게시글 수
		int totalCount = auctionService.getTotal(search);	//검색어 적용 게시글 수

		// 페이징 생성
		Auction_Paging paging = new Auction_Paging(totalCount, curPage);
//		paging.setSearch(search.getSearch()); // 검색어 추가
		model.addAttribute("paging", paging);

//		List list = auctionService.getPagingList(paging); // 전체 게시글 조회
		List list = auctionService.getSearchPagingList(paging); // 검색어 적용 게시글 조회

		model.addAttribute("list", list);
		
		return "auction/auction_list";
	}
	
//	==================================경매 상세보기==============================================
	@RequestMapping(value="/auction/auction_view.do", method=RequestMethod.GET)
	public String auctionView(Auction viewAuction, Auction_Inquire auctionInquire
			, Model model
			, HttpSession session) {
		// 게시글 번호가 1보다 작으면 목록으로 보내기
				if(viewAuction.getIdx() < 1) {
					return "redirect:/auction/auction_list.do";
				}
				
				// 경매 번호 받기
				viewAuction = auctionService.auctionView(viewAuction);
				
				// 계정 타입 검사
				
				
				// 경매 상세 정보 전달
				model.addAttribute("view", viewAuction);
				model.addAttribute("inquireList",auctionService.getInquireList(viewAuction));
				
				
				
//				System.out.println(viewAuction);
//				System.out.println(auctionInquire);
//				System.out.println(auctionService.getInquireList(viewAuction));
				return "auction/auction_view";
	}
	
//	=================================경매 문의===========================================
	@RequestMapping(value="/auction/auction_inquire.do", method=RequestMethod.POST)
	public String insertInquire(Auction_Inquire inquire) {
		
//		System.out.println(inquire);
		auctionService.insertInquire(inquire);
		return "redirect:/auction/auction_view.do?idx="+inquire.getAuction_idx();
	}
	
	@RequestMapping(value="/auction/auction_answer.do", method=RequestMethod.POST)
	public String insertAnswer(Auction_Inquire inquire) {
		
//		System.out.println(inquire);
		auctionService.updateAnswer(inquire);
		
		return "redirect:/auction/auction_view.do?idx="+inquire.getAuction_idx();
		
	}
	
//	==================================입찰==============================================
	
//	@RequestMapping(value="/auction/auctionBid.do", method=RequestMethod.POST)
//	public String auctionBid(Auction auction, BidDto bid, HttpSession session) {
//		System.out.println(bid);
//		bid.setBidder_idx((Integer) session.getAttribute("idx"));
//		return "redirect:/auction/auction_view.do?idx="+auction.getIdx();
//	}
	
	@RequestMapping(value="/auction/auctionBid.do", method=RequestMethod.POST)
	public String auctionBid(Auction auction,Model model, BidDto bid, HttpSession session) {
		bid.setBidder_idx(Integer.parseInt((String) session.getAttribute("idx")));
		System.out.println(bid);
		
		auctionService.putBid(bid);
		
		if(bid.getIsDone()==-1) {
			model.addAttribute("msg", "최소 입찰가 보다 낮은 입찰을 하셨습니다.");			
			model.addAttribute("check",0);
		model.addAttribute("url", "/auction/bidPopup.do");
			return "util/auctionAlert";
		}else if(bid.getIsDone()==1) {
		model.addAttribute("msg", "입찰성공");
		model.addAttribute("check",1);
//		model.addAttribute("url", "saveok.jsp");
		return "util/auctionAlert";
		}
		System.out.println(bid);
		
		return "util/auctionAlert";
	}
	
	@RequestMapping(value="/auction/bidPopup.do", method=RequestMethod.GET)
	public void bidPopup(Auction auction, Model model) { 
//		System.out.println(auction);
		model.addAttribute("bid", auction);
		}
	
	
//	@RequestMapping(value="/auction/bidprocess.do", method=RequestMethod.POST)
//	public void bidprocess() {
//		
//	}
	
}
