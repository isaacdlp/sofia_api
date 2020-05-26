# Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
# Please see the LICENSE file that should have been included as part of this package.
# Written by Isaac de la Pena <isaac@algonaut.us>

# ruby data_api.rb

require 'net/http'
require 'uri'
require 'json'

sid = 'mySID'

uri = URI.parse('http://sofia.conexo.vc/api')

header = {'Content-Type': 'text/json'}
payload = {
    'sid': sid,
    'metrics': {
	    '2020.01.01': {
		    '10': 3.605422555523507e-05,
			'11': 35.7,
			'12': 0.04579250190473688
		}
	}
}

http = Net::HTTP.new(uri.host, uri.port)
request = Net::HTTP::Post.new(uri.request_uri, header)
request.body = payload.to_json

response = http.request(request)

p response.code
p response.body