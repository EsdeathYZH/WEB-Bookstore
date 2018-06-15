// pages/booklist/booklist.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    books: []
  },
  // 加入购物车  
  addcart: function (e) {
    // 获取购物车的缓存数组（没有数据，则赋予一个空数组）  
    var arr = wx.getStorageSync('cart') || [];
    // 如果购物车有数据  
    if (arr.length > 0) {
      // 遍历购物车数组  
      for (var j in arr) {
        // 判断购物车内的item的id，和事件传递过来的id，是否相等  
        if (arr[j].id == e.target.dataset.id) {
          // 相等的话，给count+1（即再次添加入购物车，数量+1）  
          arr[j].count = arr[j].count + 1;
          // 最后，把购物车数据，存放入缓存（此处不用再给购物车数组push元素进去，因为这个是购物车有的，直接更新当前数组即可)
          try {
            wx.setStorageSync('cart', arr)
            wx.showToast({
              title: '添加商品成功！',
              icon: 'success',
              duration: 1000,
            })
          } catch (e) {
            console.log(e)
          }
          // 返回（在if内使用return，跳出循环节约运算，节约性能）  
          return;
        }
      }
      // 遍历完购物车后，没有对应的item项，把goodslist的当前项放入购物车数组  
      this.data.books[e.target.dataset.idx].count = 1;
      arr.push(this.data.books[e.target.dataset.idx]);
    }
    // 购物车没有数据，把item项push放入当前数据（第一次存放时）  
    else {
      this.data.books[e.target.dataset.idx].count = 1;
      arr.push(this.data.books[e.target.dataset.idx]);
    }
    // 最后，把购物车数据，存放入缓存  
    try {
      wx.setStorageSync('cart', arr)
      // 返回（在if内使用return，跳出循环节约运算，节约性能）
      wx.showToast({
        title: '添加商品成功！',
        icon: 'success',
        duration: 1000,
      })  
      return;
    } catch (e) {
      console.log(e)
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    if (that.data.books.length != 0) {
      that.data.books = [];
    }
    const requestTask = wx.request({
      url: 'http://47.106.75.68:8080/book/books',
      method: 'GET',
      success: function (res) {
        if (res.data.length != 0) {
          that.setData({
            books: res.data
          })
        } else {
          wx.showToast({
            title: ' 图书获取错误',
            icon: 'none',
            duration: 2000
          })
        }
      },
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})