package com.example.anime.ui.api.model.manga;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PopularResponse{

	@SerializedName("manga_list")
	private List<MangaListItem> mangaList;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setMangaList(List<MangaListItem> mangaList){
		this.mangaList = mangaList;
	}

	public List<MangaListItem> getMangaList(){
		return mangaList;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"PopularResponse{" + 
			"manga_list = '" + mangaList + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

    public static class MangaListItem{

        @SerializedName("endpoint")
        private String endpoint;

        @SerializedName("thumb")
        private String thumb;

        @SerializedName("upload_on")
        private String uploadOn;

        @SerializedName("title")
        private String title;

        @SerializedName("type")
        private String type;

        public void setEndpoint(String endpoint){
            this.endpoint = endpoint;
        }

        public String getEndpoint(){
            return endpoint;
        }

        public void setThumb(String thumb){
            this.thumb = thumb;
        }

        public String getThumb(){
            return thumb;
        }

        public void setUploadOn(String uploadOn){
            this.uploadOn = uploadOn;
        }

        public String getUploadOn(){
            return uploadOn;
        }

        public void setTitle(String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }

        public void setType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }

        @Override
         public String toString(){
            return
                "MangaListItem{" +
                "endpoint = '" + endpoint + '\'' +
                ",thumb = '" + thumb + '\'' +
                ",upload_on = '" + uploadOn + '\'' +
                ",title = '" + title + '\'' +
                ",type = '" + type + '\'' +
                "}";
            }
    }
}