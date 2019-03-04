/*!
 * Star Rating Vietnamese Translations
 *
 * This file must be loaded after 'star-rating.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 *
 * @see http://github.com/kartik-v/bootstrap-star-rating
 * @author Kartik Visweswaran <kartikv2@gmail.com>
 * @author KhanhC
 */
(function ($) {
    "use strict";
    $.fn.ratingLocales['vi'] = {
        defaultCaption: '{rating} Điểm',
        starCaptions: {
            0.5: 'Í ẹ',
            1: 'Không ổn',
            1.5: 'Cần cố gắng',
            2: 'Thường',
            2.5: 'Tạm',
            3: 'Cũng được',
            3.5: 'Được đấy',
            4: 'Hay',
            4.5: 'Rất hay',
            5: 'Tuyệt vời'
        },
        clearButtonTitle: 'Xóa',
        clearCaption: 'Không đánh giá'
    };
})(window.jQuery);
