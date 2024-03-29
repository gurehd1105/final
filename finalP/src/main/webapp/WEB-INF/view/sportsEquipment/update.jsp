<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="스포츠 장비 수정" />
<c:set var="description" value="스포츠 장비의 이름, 가격, 상태를 수정할 수 있는 사이트" />
<c:set var="keywords" value="장비,소모품,수정" />

<c:set var="body">
<c:set var="ctp" value="${pageContext.request.contextPath}"/>

	<!-- 장비정보 수정 폼 -->
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
			 action="${ctp}/sportsEquipment/update" enctype="multipart/form-data" method="post" id="updateForm">
		<el-form-item label="이름">
			<el-input type="text" v-model="model.itemName" name="itemName"/>			
		</el-form-item> 

		<el-form-item label="가격">
			<el-input type="number" v-model="model.itemPrice" name="itemPrice" />			
		</el-form-item> 
		
		<el-form-item label="상태">
			<el-radio-group v-model="model.equipmentActive" name="equipmentActive" class="ml-4" >
				<el-radio label="Y">주문가능</el-radio>
				<el-radio label="N">품절</el-radio>
			</el-radio-group>
		</el-form-item>

		<el-form-item label="관리자">
			<el-input type="text" v-model="model.employeeId" name="employeeId" readonly="readonly" />			
		</el-form-item>

		<el-form-item label="등록일">
			<el-input type="text" v-model="model.equipmentCreatedate" name="equipmentCreatedate" readonly="readonly" />			
		</el-form-item>
		
		<el-form-item label="수정일">
			<el-input type="text" v-model="model.equipmentUpdatedate" name="equipmentUpdatedate" readonly="readonly" />			
		</el-form-item>
		<input type="hidden" name="sportsEquipmentNo" :value="model.sportsEquipmentNo">
		<el-form-item>
			<el-button type="primary" @click="updateSubmit(form)">수정</el-button>
		</el-form-item> 
	</el-form>
	
   <!-- 장비 이미지 삭제 폼 -->
	<el-row :gutter="20">
	  <el-col :span="10" v-for="(img, i) in imgList" :key="i">
	    <el-card :label="img.sportsEquipmentImgNo" :body-style="{ padding: '15px' }">
	      <div style="padding: 14px">
	        <img :src="'/upload/sportsEquipment/' + img.sportsEquipmentImgFileName" class="image" style="width: 300%; height: 400px;"/>
	      </div>
	      <el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
	               action="${ctp}/sportsEquipment/deleteImg" 
	               enctype="multipart/form-data" method="post" :id="'deleteImg'+ i">
  				<input type="hidden" name="sportsEquipmentNo" :value="model.sportsEquipmentNo">
	          	<input type="hidden" name="sportsEquipmentImgFileName" v-model="img.sportsEquipmentImgFileName" >
	          	<input type="hidden" name="sportsEquipmentImgNo" v-model="img.sportsEquipmentImgNo">
	        <el-form-item>
	          <el-button type="primary" @click="deleteSubmit(i)">삭제</el-button>
	        </el-form-item>
	      </el-form>
	    </el-card>
	  </el-col>
	</el-row>

	<br>
	<!-- 장비이미지 추가 폼 -->
	<el-form label-position="right" ref="form" label-width="150px" status-icon class="max-w-lg"
	         action="${ctp}/sportsEquipment/insertImg" enctype="multipart/form-data" method="post" id="insertImg">
	
	 	<el-form-item label="이미지">
			<el-input type="file" v-model="img" name="img" multiple  />			
		</el-form-item> 
	
	  	<input type="hidden" name="sportsEquipmentNo" :value="model.sportsEquipmentNo">
	
	  	<el-form-item>
	    	<el-button type="primary" @click="insertSubmit(form)">추가</el-button>
	  	</el-form-item>
	
	</el-form>
	
</c:set>
<c:set var="script">
	data() {
	  	return {
	  			model: {
	    
	    			sportsEquipmentNo: '${one.sportsEquipmentNo}', 
				    itemPrice: '${one.itemPrice}',
				    employeeId: '${one.employeeId}',
				    itemName: '${one.itemName}',
				    equipmentActive: '${one.equipmentActive}',
				    equipmentCreatedate: '${one.createdate}',
				    equipmentUpdatedate: '${one.updatedate}',
	  			},
	  			
				    imgList: JSON.parse('${imgList}'),
				    img: [],	  
	  				updateModel : {},
			};
		},
		
	created() {
  			this.updateModel = {
    				sportsEquipmentNo: this.model.sportsEquipmentNo,
    				itemPrice: this.model.itemPrice,
    				employeeId: this.model.employeeId,
    				itemName: this.model.itemName,
    				equipmentActive: this.model.equipmentActive,
    				equipmentCreatedate: this.model.equipmentCreatedate,
    				equipmentUpdatedate: this.model.equipmentUpdatedate,
  				};
		},
	
	methods: {
	
		insertSubmit(){
		 	if (this.img.length === 0) {
      			alert('파일을 선택해주세요');
     		 return;
    		}
			document.getElementById('insertImg').submit();
		},
		
		deleteSubmit(i){
			const form = document.getElementById('deleteImg'+i);
			console.log('i:', i);
        	console.log('deleteSubmit Parameters:', form);
        
        	if (form) {
             	form.submit();  
        	} else {
            	console.error('Form not found.');
        	}
		},
		
	
		updateSubmit() {
			console.log('제출 전에 변경 사항이 있는지 확인');
            if (this.checkChanges()) {
            	console.log('변경 사항 있음');
            	this.compareModels();
                document.getElementById('updateForm').submit();
            } else {
                alert('변경된 내용이 없습니다.');
            }
		},
		

	    checkChanges() {
            for (const key in this.model) {
                if (this.model[key] !== this.updateModel[key]) {
                   	console.log('true');
                    return true;
                }
            }
            console.log('false');
            return false;
        },
        

 		 compareModels() {

    		for (const key in this.updateModel) {
      			if (this.updateModel[key] == this.model[key]) {
       				 console.log(`${key}: `, this.updateModel[key], `->`, this.model[key]);
      			}
    		}
 		 },
		
	}
</c:set>

<%@ include file="/inc/admin_layout.jsp"%>