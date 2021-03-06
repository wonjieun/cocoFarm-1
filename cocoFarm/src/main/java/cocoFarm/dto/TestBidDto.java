package cocoFarm.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TestBidDto {

	private Integer auction_idx;	//	대상 경매 번호 - 복합기본키. 외래키 (경매)
	private Integer amount;			//	입찰액 - 복합 기본키, 0이상
	private Timestamp bid_time;		//	입찰 시각 - null불가. 트리거 있음: 새 입찰 등록시, 시스템 시각 지정
	private Integer bidder_idx;		//	입찰자 계정번호 - 외래키 (계정) null불가
	private Integer state_code;		//	입찰 상태 코드 - 외래키. 트리거 있음 (기본값 1) null불가
	private Integer isDone;			//	입찰 종료 시각 (지불기간 만료/취소/대금지불 등)

	@Override
	public String toString() {
		return "BID [auction_idx: "+auction_idx+", amount "+amount+", bid_time "+bid_time+", bidder_idx "+bidder_idx+", state_code "+state_code+", isIn "+isDone+"]";
	}

	public Timestamp getWakeTime() {
		return this.bid_time;
	}

	
	public Integer getAuction_idx() {
			return auction_idx;
	}
	public void setAuction_idx(Integer auction_idx) {
		this.auction_idx = auction_idx;
	}

	public Integer getAmount() {
			return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getBid_time() {
		
		if(bid_time !=null) {
			return new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").format(bid_time);
		}else {
			return "----/--/-- --:--:--";
		}
	}
	public void setBid_time(Timestamp bid_time) {
		this.bid_time = bid_time;
	}

	public Integer getBidder_idx() {
		return bidder_idx;
	}
	public void setBidder_idx(Integer bidder_idx) {
		this.bidder_idx = bidder_idx;
	}

	public Integer getState_code() {
		return state_code;
	}
	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public Integer getIsDone() {
		return isDone;
	}public void setIsDone(Integer isDone) {
		this.isDone = isDone;
	}
	
}
