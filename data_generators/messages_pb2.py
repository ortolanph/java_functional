# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# NO CHECKED-IN PROTOBUF GENCODE
# source: messages.proto
# Protobuf Python Version: 5.29.3
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import runtime_version as _runtime_version
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
_runtime_version.ValidateProtobufRuntimeVersion(
    _runtime_version.Domain.PUBLIC,
    5,
    29,
    3,
    '',
    'messages.proto'
)
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x0emessages.proto\"B\n\x12VersionInformation\x12\r\n\x05major\x18\x01 \x01(\t\x12\r\n\x05minor\x18\x02 \x01(\t\x12\x0e\n\x06\x62ugfix\x18\x03 \x01(\t\")\n\x0bMessageList\x12\x1a\n\x08messages\x18\x01 \x03(\x0b\x32\x08.Message\"`\n\x07Message\x12\n\n\x02id\x18\x01 \x01(\t\x12\x1b\n\x06\x61\x63tion\x18\x02 \x01(\x0e\x32\x0b.ActionType\x12\x17\n\x06header\x18\x03 \x01(\x0b\x32\x07.Header\x12\x13\n\x04\x62ody\x18\x04 \x01(\x0b\x32\x05.Body\".\n\x06Header\x12\x11\n\tcreatedBy\x18\x01 \x01(\t\x12\x11\n\ttimestamp\x18\x02 \x01(\t\"J\n\x04\x42ody\x12\n\n\x02id\x18\x01 \x01(\t\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x15\n\x05phone\x18\x03 \x01(\x0b\x32\x06.Phone\x12\x11\n\tbirthdate\x18\x04 \x01(\t\"\'\n\x05Phone\x12\x0e\n\x06prefix\x18\x01 \x01(\t\x12\x0e\n\x06number\x18\x02 \x01(\t*0\n\nActionType\x12\n\n\x06\x44\x45LETE\x10\x00\x12\n\n\x06UPDATE\x10\x01\x12\n\n\x06\x43REATE\x10\x02\x62\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'messages_pb2', _globals)
if not _descriptor._USE_C_DESCRIPTORS:
  DESCRIPTOR._loaded_options = None
  _globals['_ACTIONTYPE']._serialized_start=392
  _globals['_ACTIONTYPE']._serialized_end=440
  _globals['_VERSIONINFORMATION']._serialized_start=18
  _globals['_VERSIONINFORMATION']._serialized_end=84
  _globals['_MESSAGELIST']._serialized_start=86
  _globals['_MESSAGELIST']._serialized_end=127
  _globals['_MESSAGE']._serialized_start=129
  _globals['_MESSAGE']._serialized_end=225
  _globals['_HEADER']._serialized_start=227
  _globals['_HEADER']._serialized_end=273
  _globals['_BODY']._serialized_start=275
  _globals['_BODY']._serialized_end=349
  _globals['_PHONE']._serialized_start=351
  _globals['_PHONE']._serialized_end=390
# @@protoc_insertion_point(module_scope)
