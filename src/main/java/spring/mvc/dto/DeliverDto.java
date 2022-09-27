/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 15, 2021
 * @version 1.0
 */
package spring.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliverDto {
	private String checkpointDate;
	private String trackingDetail;
	private String location;
	private String checkpointDeliveryStatus;
	private String checkpointDeliverySubstatus;
//    "checkpoint_date": "2021-06-11 13:11:00",
//    "tracking_detail": "Đã đến bưu cục (Arrival at PO),",
//    "location": "HUB Long Bình - Đồng Nai - Ðồng Nai",
//    "checkpoint_delivery_status": "transit",
//    "checkpoint_delivery_substatus": "transit001"

	public void showInfo() {
		System.out.println(this.checkpointDate);
		System.out.println(this.trackingDetail);
		System.out.println(this.location);
	}
}
