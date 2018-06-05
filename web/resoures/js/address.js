(function() {
    function Address() {
        this.$iosDialog2 = $('#iosDialog2');
        this.$btnLoading = $('.btn-loading');
        this.$saveBtn = $('.save-btn');

        this.init();
    }

    Object.assign(Address.prototype, {
        init: function() {
            var self = this;

            var receiver = localStorage.getItem("receiver");
            self.receiver = !!receiver? JSON.parse(receiver): {};

            self.openId = localStorage.getItem("openId");
            this.renderExists();
            this.bindEvent();
        },
        renderExists: function() {
            var self = this;
            if(!!self.receiver) {
                $('.receiver').val(self.receiver.receiver);
                $('.receiverPhone').val(self.receiver.receiverPhone);
                $('.receiverAddress').val(self.receiver.receiverAddress);
            }
        },
        bindEvent: function() {
            var self = this;
            $(document).on('click', '.save-btn' ,$.proxy(self.save, self));

            $(document).on('click', '.weui-dialog__btn',$.proxy(function(){
                $('.js_dialog').fadeOut(200);
            }, self) );
        },
        showErrorDialog: function(msg) {
            var self = this;
            $('.weui-dialog__bd').text(msg)
            self.$iosDialog2.fadeIn(200);
        },
        changeBtnStatus: function(loading) {
            var self = this;
            if(loading) {
                self.$btnLoading.css('display','block');
                self.$saveBtn.hide();
            } else {
                self.$saveBtn.css('display','block');
                self.$btnLoading.hide();
            }
        },
        save: function() {
            var self = this;

            var receiver = $('.receiver').val();
            var receiverPhone = $('.receiverPhone').val();
            var receiverAddress = $('.receiverAddress').val();

            if(!receiver) {
                self.showErrorDialog('请填写收单人姓名');
                return false;
            }

            if(!receiverPhone) {
                self.showErrorDialog('请填写收单人电话');
                return false;
            }

            var reg = /^((1[0-9][0-9])+\d{8})$/;
            if(!reg.test(receiverPhone)) {
                self.showErrorDialog('请填写正确格式的手机号');
                return false;
            }

            if(!receiverAddress) {
                self.showErrorDialog('请填写收单人地址');
                return false;
            }

            self.changeBtnStatus(true);
            var params = {
                'receiver': receiver,
                'receiverPhone': receiverPhone,
                'receiverAddress': receiverAddress,
                'openId': self.openId
            }
            $.ajax({
                url: "/userAddress/add",
                type: "POST",
                data: params,
                success: function(res) {
                    if(res.code == 200) {
                        var id = res.data.id;
                        // localStorage.setItem("receiverId", id);
                        params.id = id;

                        self.receiver = params;
                        localStorage.setItem("receiver", JSON.stringify(self.receiver));

                        location.href= "/insurance";
                    } else {
                        self.showErrorDialog(res.msg);
                    }

                    self.changeBtnStatus(false);


                },
                error: function(res) {
                    self.showErrorDialog(res.msg);
                    self.changeBtnStatus(false);
                }
            })
        }
    })

    new Address();
})();