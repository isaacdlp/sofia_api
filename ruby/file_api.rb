# Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
# Please see the LICENSE file that should have been included as part of this package.
# Written by Isaac de la Pena <isaac@algonaut.us>

# ruby file_api.rb

require 'net/http'
require 'uri'
require 'json'

sid = 'mySID'

uri = URI('https://sofia.conexo.vc/xls/' + sid)
request = Net::HTTP::Post.new(uri)
form_data = [
    ['xls', File.open('../Sofia_Metrics_Template.xlsx')],
    ['all_dates', 'true']
]

request.set_form form_data, 'multipart/form-data'
response = Net::HTTP.start(uri.hostname, uri.port) do |http|
  http.request(request)
end

p response.code
p response.body